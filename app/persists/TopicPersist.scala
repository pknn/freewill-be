package persists

import java.sql.Timestamp

import com.google.inject.{Inject, Singleton}
import persists.generated.Tables.{Topics, TopicsRow}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class TopicPersist @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
  def find(filter: TopicFilter): Future[Seq[TopicsRow]] =
    db.run {
      Topics
        .filterOpt(filter.topicId)(_.id === _)
        .result
    }

  def create(
    title: String,
    description: Option[String],
    score: Int
  ): Future[Int] =
    db.run {
      Topics.map(row => (row.title, row.description, row.score)) += (title, description, score)
    }

  def update(
    id: String,
    title: String,
    description: Option[String],
    score: Int
  ): Future[Int] =
    db.run {
      val updatingFields = Topics.filter(_.id === id).map(row => (row.title, row.description, row.score, row.updatedAt))
      updatingFields.update((title, description, score, new Timestamp(System.currentTimeMillis)))
    }

}

case class TopicFilter(topicId: Option[String])

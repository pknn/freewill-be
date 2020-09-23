package persists

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import persists.generated.Tables.Topics
import scala.concurrent.Future
import persists.generated.Tables.TopicsRow

@Singleton()
class TopicPersist @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
  def find(filter: TopicFilter): Future[Seq[TopicsRow]] =
    db run {
      Topics
        .filterOpt(filter.topicId)(_.id === _)
        .result
    }

  def create(title: String, description: Option[String], score: Int) =
    db run {
      Topics.map(row =>
        (row.title, row.description, row.score)
      ) += (title, description, score)
    }

}

case class TopicFilter(topicId: Option[String])

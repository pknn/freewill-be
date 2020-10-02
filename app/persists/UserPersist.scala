package persists

import java.sql.Timestamp

import com.google.inject.{Inject, Singleton}
import persists.generated.Tables
import persists.generated.Tables.Users
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class UserPersist @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
  def find(filter: UserFilter): Future[Seq[Tables.UsersRow]] =
    db.run {
      Users
        .filterOpt(filter.userId)(_.id === _)
        .filterOpt(filter.userName)(_.username === _)
        .filterOpt(filter.userEmail)(_.email === _)
        .filter(_.deletedAt.isEmpty)
        .result
    }

  def create(
    username: String,
    email: String,
    encryptedPassword: String
  ): Future[Int] =
    db.run {
      Users.map(row => (row.username, row.email, row.encryptedPassword)) += (username, email, encryptedPassword)
    }

  def edit(
    id: String,
    username: String,
    email: String
  ): Future[Int] =
    db.run {
      val updatingFields = Users.filter(_.id === id).map(row => (row.username, row.email, row.updatedAt))
      updatingFields.update((username, email, new Timestamp(System.currentTimeMillis())))
    }

  def delete(id: String): Future[Int] =
    db.run {
      Users.filter(_.id === id).map(_.deletedAt).update(Some(new Timestamp(System.currentTimeMillis())))
    }

}

case class UserFilter(
  userId: Option[String],
  userName: Option[String],
  userEmail: Option[String])

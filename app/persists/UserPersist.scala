package persists

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
        .filterOpt(filter.userName)(_.userName === _)
        .filterOpt(filter.userEmail)(_.email === _)
        .filter(_.deletedAt.isEmpty)
        .result
    }

  def create(firstName: String, lastName: String, userName: String, email: String, )
}

case class UserFilter(
  userId: Option[String],
  userName: Option[String],
  userEmail: Option[String])

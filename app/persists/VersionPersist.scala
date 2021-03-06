package persists

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{ExecutionContext, Future}
import persists.generated.Tables.Version
import persists.generated.Tables.VersionRow

@Singleton()
class VersionPersist @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
  def getLatestVersion: Future[Option[VersionRow]] =
    db.run {
      Version
        .sortBy(_.id * -1)
        .result
        .headOption
    }

  def create(version: String): Future[Int] =
    db.run {
      Version
        .map(version => (version.appVersion)) += version
    }

}

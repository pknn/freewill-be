package useCases

import com.google.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import persists.VersionPersist
import models.Version
import persists.generated.Tables.VersionRow
import exceptions.VersionNotFoundException

@Singleton()
class VersionUseCase @Inject() (
  versionPersist: VersionPersist
)(implicit ec: ExecutionContext) {
  def getLatestVersion: Future[Version] = {
    val futureMaybeVersionRow: Future[Option[VersionRow]] =
      versionPersist.getLatestVersion
    val futureVersionRow: Future[VersionRow] =
      futureMaybeVersionRow.map(_.getOrElse(throw new VersionNotFoundException))
    futureVersionRow.map(Version.apply)
  }

  def addVersion(version: String): Future[Unit] =
    versionPersist.create(version).map(_ => ())

}

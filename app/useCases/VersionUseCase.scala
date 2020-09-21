package useCases

import com.google.inject.{Singleton, Inject}
import scala.concurrent.{ExecutionContext, Future}
import persists.VersionPersist
import models.Version

@Singleton()
class VersionUseCase @Inject() (
  versionPersist: VersionPersist
)(implicit ec: ExecutionContext) {
  def getLatestVersion: Future[Version] = {
    val version = versionPersist.getLatestVersion
    version.map(Version.apply)
  }
}

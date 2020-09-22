package presenters

import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.{Format, Json, JsonConfiguration}
import models.Version

case class VersionPresenter(appVersion: String)

object VersionPresenter {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[VersionPresenter] =
    Json.format[VersionPresenter]

  def apply(version: Version): VersionPresenter =
    VersionPresenter(appVersion = version.appVersion)

}

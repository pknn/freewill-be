package bodies

import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.{Format, Json, JsonConfiguration}

case class CreateVersionBody(appVersion: String)

object CreateVersionBody {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[CreateVersionBody] = Json.format[CreateVersionBody]
}

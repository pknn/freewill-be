package bodies

import play.api.libs.json.{Format, Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase

case class UserBody(
  username: String,
  email: String,
  password: String)

object UserBody {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[UserBody] = Json.format[UserBody]
}

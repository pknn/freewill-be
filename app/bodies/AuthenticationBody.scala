package bodies

import play.api.libs.json.{Format, Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase

case class AuthenticationBody(username: String, password: String)

object AuthenticationBody {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[AuthenticationBody] = Json.format[AuthenticationBody]
}

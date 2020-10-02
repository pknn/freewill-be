package bodies

import play.api.libs.json.JsonConfiguration
import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.Format
import play.api.libs.json.Json

case class CreateTopicBody(
  title: String,
  description: Option[String],
  score: Int)

object CreateTopicBody {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[CreateTopicBody] = Json.format[CreateTopicBody]

}

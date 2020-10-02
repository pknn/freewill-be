package bodies

import play.api.libs.json.JsonConfiguration
import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.Format
import play.api.libs.json.Json

case class TopicBody(
  title: String,
  description: Option[String],
  score: Int)

object TopicBody {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[TopicBody] = Json.format[TopicBody]
}

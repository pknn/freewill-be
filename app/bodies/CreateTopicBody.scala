package bodies

import play.api.libs.json.JsonConfiguration
import play.api.libs.json.JsonNaming.SnakeCase

case class CreateTopicBody(title: String,
                           description: Option[String],
                           score: Int
)

object CreateTopicBody {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
}

package presenters

import java.sql.Timestamp
import models.Topic
import play.api.libs.json.JsonConfiguration
import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.Format
import play.api.libs.json.Json
import java.text.SimpleDateFormat
import play.api.libs.json.JsValue
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsString

case class TopicPresenter(
  id: String,
  title: String,
  description: Option[String],
  score: Int,
  createdAt: String)

object TopicPresenter {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[TopicPresenter] = Json.format[TopicPresenter]

  def apply(topic: Topic): TopicPresenter =
    TopicPresenter(
      id = topic.id,
      title = topic.title,
      description = topic.maybeDescription,
      score = topic.score,
      createdAt = topic.createdAt.toString
    )

}

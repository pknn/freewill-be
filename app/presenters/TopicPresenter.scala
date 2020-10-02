package presenters

import models.Topic
import play.api.libs.json.{Format, Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase

case class TopicPresenter(
  id: String,
  title: String,
  description: Option[String],
  score: Int,
  createdAt: String,
  updatedAt: String)

object TopicPresenter {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[TopicPresenter] = Json.format[TopicPresenter]

  def apply(topic: Topic): TopicPresenter =
    TopicPresenter(
      id = topic.id,
      title = topic.title,
      description = topic.maybeDescription,
      score = topic.score,
      createdAt = topic.createdAt.toString,
      updatedAt = topic.updatedAt.toString
    )

}

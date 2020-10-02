package models

import java.sql.Timestamp

import bodies.TopicBody
import persists.generated.Tables.TopicsRow

case class Topic(
  id: String,
  title: String,
  maybeDescription: Option[String],
  score: Int,
  createdAt: Timestamp,
  updatedAt: Timestamp)

object Topic {
  def apply(topicRow: TopicsRow): Topic =
    Topic(
      id = topicRow.id,
      title = topicRow.title,
      maybeDescription = topicRow.description,
      score = topicRow.score,
      createdAt = topicRow.createdAt,
      updatedAt = topicRow.updatedAt
    )

}

case class TopicForm(
  title: String,
  maybeDescription: Option[String],
  score: Int)

object TopicForm {
  def apply(topicBody: TopicBody): TopicForm =
    TopicForm(title = topicBody.title, maybeDescription = topicBody.description, score = topicBody.score)

}

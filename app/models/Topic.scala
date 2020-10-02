package models

import java.sql.Timestamp

import bodies.TopicBody
import persists.CompleteTopicRow

case class Topic(
  id: String,
  title: String,
  maybeDescription: Option[String],
  score: Int,
  maybeUser: Option[User],
  createdAt: Timestamp,
  updatedAt: Timestamp)

object Topic {
  def apply(completeTopicRow: CompleteTopicRow): Topic =
    Topic(
      id = completeTopicRow.topicsRow.id,
      title = completeTopicRow.topicsRow.title,
      maybeDescription = completeTopicRow.topicsRow.description,
      score = completeTopicRow.topicsRow.score,
      maybeUser = completeTopicRow.maybeUsersRow.map(User.apply),
      createdAt = completeTopicRow.topicsRow.createdAt,
      updatedAt = completeTopicRow.topicsRow.updatedAt
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

package models

import java.sql.Timestamp
import persists.generated.Tables.TopicsRow

case class Topic(
  id: String,
  title: String,
  maybeDescription: Option[String],
  score: Int,
  createdAt: Timestamp)

object Topic {
  def apply(topicRow: TopicsRow): Topic =
    Topic(
      id = topicRow.id,
      title = topicRow.title,
      maybeDescription = topicRow.description,
      score = topicRow.score,
      createdAt = topicRow.createdAt
    )

}

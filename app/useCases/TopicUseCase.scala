package useCases

import com.google.inject.{Inject, Singleton}
import persists.TopicPersist
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import models.Topic
import persists.TopicFilter

@Singleton()
class TopicUseCase @Inject() (
  topicPersist: TopicPersist
)(implicit ec: ExecutionContext) {
  def findTopics(maybeTopicId: Option[String]): Future[Seq[Topic]] = {
    val filter = TopicFilter(topicId = maybeTopicId)
    val topicRows = topicPersist.find(filter)

    topicRows
      .map(_.map(Topic.apply))
  }

}
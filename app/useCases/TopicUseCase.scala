package useCases

import com.google.inject.{Inject, Singleton}
import models.{Topic, TopicForm}
import persists.generated.Tables
import persists.{TopicFilter, TopicPersist}

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class TopicUseCase @Inject() (topicPersist: TopicPersist)(implicit ec: ExecutionContext) {
  def findTopics(maybeTopicId: Option[String]): Future[Seq[Topic]] = {
    val filter: TopicFilter = TopicFilter(topicId = maybeTopicId)
    val topicRows: Future[Seq[Tables.TopicsRow]] = topicPersist.find(filter)

    topicRows
      .map(_.map(Topic.apply))
  }

  def createTopic(topicForm: TopicForm): Future[Unit] = {
    val result = topicPersist.create(
      topicForm.title,
      topicForm.maybeDescription,
      topicForm.score
    )

    result.map(_ => ())
  }

  def updateTopic(id: String, topicForm: TopicForm): Future[Unit] = {
    val result = topicPersist.update(id, topicForm.title, topicForm.maybeDescription, topicForm.score)

    result.map(_ => ())
  }

  def deleteTopic(id: String): Future[Unit] = {
    val result = topicPersist.delete(id)

    result.map(_ => ())
  }

}

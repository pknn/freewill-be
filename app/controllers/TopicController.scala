package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import scala.concurrent.ExecutionContext
import play.api.mvc.BaseController
import play.api.mvc.Action
import play.api.mvc.AnyContent
import useCases.TopicUseCase
import presenters.TopicPresenter
import commons.ApiResults

@Singleton()
class TopicController @Inject() (
  protected val controllerComponents: ControllerComponents,
  topicUseCase: TopicUseCase
)(implicit ec: ExecutionContext)
    extends BaseController {
  def get(topicId: Option[String]): Action[AnyContent] =
    Action.async {
      val topics = topicUseCase.findTopics(topicId)
      val topicPresenters = topics.map(_.map(TopicPresenter.apply))

      ApiResults.async(topicPresenters)
    }

}

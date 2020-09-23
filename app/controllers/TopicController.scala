package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import useCases.TopicUseCase
import play.api.mvc.BaseController
import commons.ApiResults
import play.api.mvc.Action
import play.api.mvc.AnyContent
import presenters.TopicPresenter
import scala.concurrent.ExecutionContext
import bodies.TopicBody

@Singleton()
class TopicController @Inject() (
  val controllerComponents: ControllerComponents,
  topicUseCase: TopicUseCase
)(implicit ec: ExecutionContext)
    extends BaseController {
  def get(topicId: Option[String]): Action[AnyContent] =
    Action.async {
      val topics = topicUseCase.findTopics(topicId)
      val topicsPresenter = topics.map(_.map(TopicPresenter.apply))

      ApiResults.async(topicsPresenter)
    }

  def create: Action[TopicBody] =
    Action.async(parse.json[TopicBody]) { request =>
      val body: TopicBody = request.body
      val result = topicUseCase.create(body)

      ApiResults.async(result.map(_ => "Success"))
    }

}

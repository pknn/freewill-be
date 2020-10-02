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
import bodies.TopicBody
import models.TopicForm

@Singleton()
class TopicController @Inject() (
  protected val controllerComponents: ControllerComponents,
  topicUseCase: TopicUseCase
)(implicit ec: ExecutionContext)
    extends BaseController {
  def get(topicId: Option[String], userId: Option[String]): Action[AnyContent] =
    Action.async {
      val topics = topicUseCase.findTopics(topicId, userId)
      val topicPresenters = topics.map(_.map(TopicPresenter.apply))

      ApiResults.async(topicPresenters)
    }

  def create: Action[TopicBody] =
    Action.async(parse.json[TopicBody]) { request =>
      val body: TopicBody = request.body
      val topicForm: TopicForm = TopicForm(body)
      val result = topicUseCase.createTopic(topicForm)

      ApiResults.async(result.map(_ => "Success"))
    }

  def update(topicId: String): Action[TopicBody] =
    Action.async(parse.json[TopicBody]) { request =>
      val body: TopicBody = request.body
      val topicForm: TopicForm = TopicForm(body)
      val result = topicUseCase.updateTopic(topicId, topicForm)

      ApiResults.async(result.map(_ => "Success"))
    }

  def delete(topicId: String): Action[AnyContent] =
    Action.async {
      val result = topicUseCase.deleteTopic(topicId)

      ApiResults.async(result.map(_ => "Success"))
    }

}

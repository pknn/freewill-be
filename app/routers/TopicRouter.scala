package routers

import com.google.inject.Inject
import controllers.TopicController
import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class TopicRouter @Inject() (topicController: TopicController) extends SimpleRouter {
  def routes: Routes = {
    case GET(p"/" ? q_?"topic_id=$topicId" ? q_?"user_id=$userId") =>
      topicController.get(topicId = topicId, userId = userId)
    case POST(p"/")                                                => topicController.create
    case PUT(p"/$topicId")                                         => topicController.update(topicId)
    case DELETE(p"/$topicId")                                      => topicController.delete(topicId)
  }

}

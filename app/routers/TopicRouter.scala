package routers

import play.api.routing.Router._
import play.api.routing.sird._
import com.google.inject.Inject
import controllers.TopicController
import play.api.routing.SimpleRouter
import play.api.routing.Router

class TopicRouter @Inject() (topicController: TopicController) extends SimpleRouter {
  def routes: Routes = {
    case GET(p"/" ? q_?"topic_id=$topicId") => topicController.get(topicId = topicId)
    case POST(p"/")                         => topicController.create
  }

}

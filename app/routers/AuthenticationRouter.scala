package routers

import com.google.inject.Inject
import controllers.AuthenticationController
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class AuthenticationRouter @Inject() (authenticationController: AuthenticationController) extends SimpleRouter {
  def routes: Routes = {
    case POST(p"/login") => authenticationController.login
  }

}

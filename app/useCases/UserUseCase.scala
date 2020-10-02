package useCases

import com.google.inject.{Inject, Singleton}
import models.{User, UserForm}
import persists.{UserFilter, UserPersist}

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class UserUseCase @Inject() (userPersist: UserPersist)(implicit ec: ExecutionContext) {
  def findUsers(
    maybeUserId: Option[String] = None,
    maybeUsername: Option[String] = None,
    maybeUserEmail: Option[String] = None
  ): Future[Seq[User]] = {
    val filter = UserFilter(maybeUserId, maybeUsername, maybeUserEmail)
    val result = userPersist.find(filter)

    result.map(_.map(User.apply))
  }

  def createUser(userForm: UserForm): Future[Unit] = {
    val result = userPersist.create(userForm.username, userForm.email, userForm.encryptedPassword)

    result.map(_ => ())
  }

}

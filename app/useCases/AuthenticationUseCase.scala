package useCases

import com.google.inject.{Inject, Singleton}
import commons.{Cryptography, JWT}
import configurations.ApplicationConfiguration
import exceptions.AuthenticationUserNotFoundException
import models.Authentication
import persists.{TokenPersist, UserFilter, UserPersist}

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class AuthenticationUseCase @Inject() (
  userPersist: UserPersist,
  tokenPersist: TokenPersist
)(implicit ec: ExecutionContext,
  applicationConfiguration: ApplicationConfiguration) {
  def login(username: String, password: String): Future[Authentication] =
    for {
      users <- userPersist.find(UserFilter(username = Some(username)))
      user = users.headOption match {
               case Some(user) => user
               case None       => throw new AuthenticationUserNotFoundException
             }
      _ = Cryptography.checkPassword(password, user.encryptedPassword)
      (accessToken, refreshToken) = JWT.getTokens(user.id)
      _ <- tokenPersist.create(user.id, accessToken, refreshToken)
    } yield Authentication(accessToken, refreshToken)

  def logout(userId: String): Future[Unit] = {
    val result = tokenPersist.delete(userId)

    result.map(_ => ())
  }

}

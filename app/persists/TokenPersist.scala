package persists

import com.google.inject.{Inject, Singleton}
import persists.generated.Tables.{Tokens, TokensRow}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class TokenPersist @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
  def find(userId: String): Future[Option[TokensRow]] =
    db.run {
      Tokens.filter(_.userId === userId).result.headOption
    }

  def create(
    userId: String,
    accessToken: String,
    refreshToken: String
  ): Future[Int] =
    db.run {
      Tokens.filter(_.userId === userId).delete.andThen(
        Tokens.map(row => (row.userId, row.accessToken, row.refreshToken)) += (userId, accessToken, refreshToken)
      )
    }

  def delete(userId: String): Future[Int] = db.run(Tokens.filter(_.userId === userId).delete)
}

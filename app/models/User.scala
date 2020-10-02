package models

import java.sql.Timestamp

import bodies.UserBody
import commons.{Cryptography, Validation}
import persists.generated.Tables.UsersRow

case class User(
  username: String,
  email: String,
  encryptedPassword: String,
  createdAt: Timestamp,
  updatedAt: Timestamp)

object User {
  def apply(usersRow: UsersRow): User =
    User(usersRow.username, usersRow.email, usersRow.encryptedPassword, usersRow.createdAt, usersRow.updatedAt)

}

case class UserForm(
  username: String,
  email: String,
  encryptedPassword: String)

object UserForm {
  def apply(userBody: UserBody): UserForm = {
    Validation.validateUsername(userBody.username)
    Validation.validateEmail(userBody.email)
    Validation.validatePassword(userBody.password)

    val encryptedPassword = Cryptography.encryptPassword(userBody.password)

    UserForm(userBody.username, userBody.email, encryptedPassword)
  }

}

package models

import java.sql.Timestamp

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

package exceptions

import play.api.i18n.Messages

class ApplicationException(
  code: String,
  message: String = "",
  cause: Throwable = null
) extends Exception(message, cause) {
  def apiErrorCode: String = code
}

class ExpectedException(
  code: String = "",
  message: String = "",
  cause: Throwable = null,
  errorMessageKey: String = "",
  errorMessageParams: Seq[String] = Seq.empty
) extends ApplicationException(code, message, cause) {
  def apiErrorMessage(implicit messages: Messages): String =
    messages
      .translate(errorMessageKey, errorMessageParams)
      .getOrElse(message)

}

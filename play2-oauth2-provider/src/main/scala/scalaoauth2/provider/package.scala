package scalaoauth2

import play.api.mvc.Result

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

package object provider {

  /**
   * Support synchronous Result for Playframework
   *
   * <h3>Create controller for issue access token</h3>
   * @example {{{
   * import scalaoauth2.provider._
   * object OAuth2Controller extends Controller with OAuth2Provider {
   *   def accessToken = Action { implicit request =>
   *     await(issueAccessToken(new MyDataHandler()))
   *   }
   * }
   * }}}
   *
   * <h3>Register routes</h3>
   * @example {{{
   * POST /oauth2/access_token controllers.OAuth2Controller.accessToken
   * }}}
   *
   * <h3>Authorized</h3>
   * @example {{{
   * import scalaoauth2.provider._
   * object BookController extends Controller with OAuthProvider {
   *   def list = Action { implicit request =>
   *     await(authorize(new MyDataHandler()) { authInfo =>
   *       val user = authInfo.user // User is defined on your system
   *       // access resource for the user
   *       Future.successful(Ok)
   *     })
   *   }
   * }
   * }}}
   * @param f callback
   * @param timeout maximum wait time
   * @return Await and return the result.
   */
  @deprecated("Use Await in your own", "0.13.0")
  def await(f: Future[Result], timeout: Duration = 60.seconds): Result = Await.result(f, timeout)

}

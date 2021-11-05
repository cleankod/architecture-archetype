package sso.domain.authentication.entrypoint

import spock.lang.Unroll
import sso.BaseRestSpec
import sso.domain.authentication.core.domain.Authentication
import sso.domain.authentication.core.domain.AuthenticationFailedReason
import sso.domain.authentication.entrypoint.AuthenticateWithUsernameAndPasswordController.AuthenticationRequest
import sso.domain.user.core.domain.UserId

class AuthenticateWithUsernameAndPasswordSpec extends BaseRestSpec {

  @Unroll
  def "should authenticate by username and password"() {
    given:
    def param = new AuthenticationRequest(username, password)

    when:
    def responseObject = post("/authenticate/unp", param, Authentication.class)

    then:
    responseObject.token != null
    responseObject.user.userId == UserId.of(userId)

    where:
    username    | password      || userId
    "bumblebee" | "P@ssw0rd"    || 123L
    "heatwave"  | "P@ssw0rd123" || 124L
  }

  def "should not authenticate by username and password due to bad credentials"() {
    given:
    def param = new AuthenticationRequest(username, password)

    when:
    def response = post("/authenticate/unp", param)

    then:
    response.getStatusLine().getStatusCode() == 400
    transform(response, AuthenticationFailedReason.class) == AuthenticationFailedReason.BAD_CREDENTIALS

    where:
    username    | password
    "bumblebee" | "wrong-password"
    "hotshot"   | "P@ssw0rd"
  }
}

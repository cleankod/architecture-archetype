package sso.domain.authentication.entrypoint


import sso.BaseRestSpec
import sso.domain.authentication.core.domain.Authentication
import sso.domain.authentication.core.domain.AuthenticationFailedReason
import sso.domain.facebook.entrypoint.AuthenticateWithFacebookController
import sso.domain.user.core.domain.Username

class AuthenticateWithFacebookSpec extends BaseRestSpec {

  def "should authenticate by facebook"() {
    given:
    def param = new AuthenticateWithFacebookController.AuthenticationRequest(facebookId, facebookToken)

    when:
    def responseObject = post("/authenticate/facebook", param, Authentication.class)

    then:
    responseObject.token != null
    responseObject.user.username == Username.of(userName)

    where:
    facebookId | facebookToken || userName
    "d80e08d6" | "43cc8f2e-389d-11ec-b097-ef579a0a53dd" || "John doe"
    "14630188" | "4888bb14-389d-11ec-bea0-c7563e55e7bc" || "Alfredo Arachidi"
  }

  def "should not authenticate by facebook due to bad credentials"() {
    given:
    def param = new AuthenticateWithFacebookController.AuthenticationRequest(facebookId, facebookToken)

    when:
    def response = post("/authenticate/facebook", param)

    then:
    response.getStatusLine().getStatusCode() == 400
    transform(response, AuthenticationFailedReason.class) == AuthenticationFailedReason.BAD_CREDENTIALS

    where:
    facebookId | facebookToken
    "d80e08d6" | "67c3eb10-38b2-11ec-a26a-6fe4226fced3"
    "85e81d90" | "43cc8f2e-389d-11ec-b097-ef579a0a53dd"
  }
}

package sso.domain.authentication.entrypoint

import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.ByteArrayEntity
import sso.BaseRestSpec
import sso.domain.authentication.core.domain.Authentication
import sso.domain.user.core.domain.Username
import sso.util.JsonTransformer

class AuthenticateWithFacebookSpec extends BaseRestSpec {

  def "should authenticate by facebook"() {
    given:
    HttpUriRequest request = new HttpPost(baseUrl + "/authenticate/facebook")

    def param = new AuthenticateWithFacebookController.AuthenticationRequest("d80e08d6", "43cc8f2e-389d-11ec-b097-ef579a0a53dd")
    HttpEntity entity = new ByteArrayEntity(JsonTransformer.toJson(param).getBytes("UTF-8"))
    request.setEntity(entity)

    when:
    def responseObject = execute(request, Authentication.class)

    then:
    responseObject.token != null
    responseObject.user.username == Username.of("John doe")
  }
}

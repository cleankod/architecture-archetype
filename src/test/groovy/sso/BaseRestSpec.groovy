package sso

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import spock.lang.Specification
import sso.initialization.ApplicationInitializer
import sso.util.JsonTransformer

class BaseRestSpec extends Specification {
  static init = true
  def baseUrl = "http://localhost:8080"

  @SuppressWarnings('unused')
  def setupSpec() {
    if (init) {
      ApplicationInitializer.main(new String[0])
      init = false
    }
  }

  static <T> T execute(HttpUriRequest request, Class<T> classOfT) {
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request)
    def response = EntityUtils.toString(httpResponse.getEntity())
    return JsonTransformer.fromJson(response, classOfT)
  }
}

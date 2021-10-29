package sso

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.ByteArrayEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import spock.lang.Specification
import sso.initialization.ApplicationInitializer
import sso.util.JsonTransformer

import java.nio.charset.StandardCharsets

class BaseRestSpec extends Specification {
  static init = true
  static final baseUrl = "http://localhost:8080"

  @SuppressWarnings('unused')
  def setupSpec() {
    if (init) {
      ApplicationInitializer.main(new String[0])
      init = false
    }
  }

  static HttpResponse post(String path, def param) {
    HttpUriRequest request = new HttpPost(baseUrl + path)
    HttpEntity entity = transform(param)
    request.setEntity(entity)
    return execute(request)
  }

  static <T> T post(String path, Object param, Class<T> classOfT) {
    HttpResponse response = post(path, param)
    return transform(response, classOfT)
  }

  static <T> T execute(HttpUriRequest request, Class<T> classOfT) {
    HttpResponse httpResponse = execute(request)
    return transform(httpResponse, classOfT)
  }

  static HttpResponse execute(HttpUriRequest request) {
    return HttpClientBuilder.create().build().execute(request)
  }

  static <T> T transform(HttpResponse httpResponse, Class<T> classOfT) {
    def response = EntityUtils.toString(httpResponse.getEntity())
    return JsonTransformer.fromJson(response, classOfT)
  }

  static HttpEntity transform(Object param) {
    new ByteArrayEntity(JsonTransformer.toJson(param).getBytes(StandardCharsets.UTF_8))
  }
}

package sso.domain.authentication.entrypoint;

import static spark.Spark.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spark.Request;
import spark.Response;
import sso.domain.authentication.core.usecase.AuthenticateWithFacebookUseCase;
import sso.domain.facebook.core.domain.FacebookId;
import sso.domain.facebook.core.domain.FacebookToken;
import sso.util.JsonTransformer;

public class AuthenticateWithFacebookController {

  public AuthenticateWithFacebookController(final AuthenticateWithFacebookUseCase authenticateWithFacebookUseCase) {
    post("/authenticate/facebook", (Request req, Response res) -> {
      AuthenticationRequest authenticationRequest = JsonTransformer.fromJson(req.body(), AuthenticationRequest.class);
      return authenticateWithFacebookUseCase.execute(FacebookId.of(authenticationRequest.getId()), FacebookToken.of(authenticationRequest.getToken()))
          .fold(
              JsonTransformer::toJson,
              authenticationFailedReason -> {
                res.status(400);
                return authenticationFailedReason;
              }
          );
    });
  }

  @Getter
  @AllArgsConstructor
  static class AuthenticationRequest {
    private final String id;
    private final String token;
  }
}

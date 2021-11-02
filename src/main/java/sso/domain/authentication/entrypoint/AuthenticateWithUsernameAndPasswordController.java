package sso.domain.authentication.entrypoint;

import static spark.Spark.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spark.Request;
import spark.Response;
import sso.domain.authentication.core.domain.PlainPassword;
import sso.domain.authentication.core.usecase.AuthenticateWithUsernameAndPasswordUseCase;
import sso.domain.user.core.domain.Username;
import sso.util.JsonTransformer;

public class AuthenticateWithUsernameAndPasswordController {

  public AuthenticateWithUsernameAndPasswordController(AuthenticateWithUsernameAndPasswordUseCase authenticateWithUsernameAndPasswordUseCase) {
    post("/authenticate/unp", (Request req, Response res) -> {
      var authenticationRequest = JsonTransformer.fromJson(req.body(), AuthenticationRequest.class);
      return authenticateWithUsernameAndPasswordUseCase
          .execute(Username.of(authenticationRequest.getUsername()), PlainPassword.of(authenticationRequest.getPassword()))
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
    private final String username;
    private final String password;
  }
}

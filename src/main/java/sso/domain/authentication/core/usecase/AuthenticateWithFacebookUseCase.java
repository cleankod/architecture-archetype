package sso.domain.authentication.core.usecase;

import lombok.RequiredArgsConstructor;
import sso.domain.authentication.core.domain.Authentication;
import sso.domain.authentication.core.domain.AuthenticationFailedReason;
import sso.domain.authentication.core.domain.Token;
import sso.domain.facebook.core.domain.FacebookId;
import sso.domain.facebook.core.domain.FacebookToken;
import sso.domain.facebook.core.gateway.FacebookAuthenticationService;
import sso.domain.user.core.domain.User;
import sso.domain.user.core.domain.Username;
import sso.util.domain.Result;

@RequiredArgsConstructor
public class AuthenticateWithFacebookUseCase {
  private final FacebookAuthenticationService authenticationService;

  public Result<Authentication, AuthenticationFailedReason> execute(FacebookId facebookId, FacebookToken facebookToken) {
    return authenticationService.authenticate(facebookId, facebookToken)
        .successMap(facebookName ->
            Authentication.builder()
                .token(Token.generate())
                .user(
                    User.builder()
                        .username(Username.of(facebookName.value()))
                        .build()
                )
                .build()
        );
  }
}

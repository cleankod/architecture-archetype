package sso.domain.authentication.core.usecase;

import lombok.RequiredArgsConstructor;
import sso.domain.authentication.core.domain.Authentication;
import sso.domain.authentication.core.domain.AuthenticationFailedReason;
import sso.domain.authentication.core.domain.HashedPassword;
import sso.domain.authentication.core.domain.PlainPassword;
import sso.domain.authentication.core.domain.Token;
import sso.domain.user.core.domain.Username;
import sso.domain.user.core.gateway.UserRepository;
import sso.util.domain.Result;

@RequiredArgsConstructor
public class AuthenticateWithUsernameAndPasswordUseCase {
  private final UserRepository userRepository;

  public Result<Authentication, AuthenticationFailedReason> execute(Username username, PlainPassword plainPassword) {
    return userRepository.find(username, HashedPassword.of(plainPassword))
        .map(user ->
            Authentication.builder()
                .user(user)
                .token(Token.generate())
                .build()
        )
        .map(Result::<Authentication, AuthenticationFailedReason>successful)
        .orElseGet(() -> Result.fail(AuthenticationFailedReason.BAD_CREDENTIALS));
  }
}

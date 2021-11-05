package sso.domain.authentication.config;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import sso.domain.authentication.core.usecase.AuthenticateWithUsernameAndPasswordUseCase;
import sso.domain.authentication.entrypoint.AuthenticateWithUsernameAndPasswordController;
import sso.domain.user.core.gateway.UserRepository;

public class AuthenticationDomainConfiguration {

  @Provides
  @Singleton
  public AuthenticateWithUsernameAndPasswordUseCase authenticateWithUsernameAndPasswordUseCase(UserRepository userRepository) {
    return new AuthenticateWithUsernameAndPasswordUseCase(userRepository);
  }

  @Provides
  @Singleton
  public AuthenticateWithUsernameAndPasswordController authenticateWithUsernameAndPasswordController(
      AuthenticateWithUsernameAndPasswordUseCase authenticateWithUsernameAndPasswordUseCase) {
    return new AuthenticateWithUsernameAndPasswordController(authenticateWithUsernameAndPasswordUseCase);
  }
}

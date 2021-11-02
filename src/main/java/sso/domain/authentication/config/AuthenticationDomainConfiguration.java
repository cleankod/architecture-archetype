package sso.domain.authentication.config;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import sso.domain.authentication.core.usecase.AuthenticateWithFacebookUseCase;
import sso.domain.authentication.core.usecase.AuthenticateWithUsernameAndPasswordUseCase;
import sso.domain.authentication.entrypoint.AuthenticateWithFacebookController;
import sso.domain.authentication.entrypoint.AuthenticateWithUsernameAndPasswordController;
import sso.domain.facebook.core.gateway.FacebookAuthenticationService;
import sso.domain.facebook.provider.FacebookAuthenticationStubService;
import sso.domain.user.core.gateway.UserRepository;

public class AuthenticationDomainConfiguration {

  @Provides
  @Singleton
  public FacebookAuthenticationService facebookAuthenticationService() {
    return new FacebookAuthenticationStubService();
  }

  @Provides
  @Singleton
  public AuthenticateWithFacebookUseCase authenticateWithFacebookUseCase(FacebookAuthenticationService facebookAuthenticationService) {
    return new AuthenticateWithFacebookUseCase(facebookAuthenticationService);
  }

  @Provides
  @Singleton
  public AuthenticateWithFacebookController authenticateWithFacebookController(AuthenticateWithFacebookUseCase authenticateWithFacebookUseCase) {
    return new AuthenticateWithFacebookController(authenticateWithFacebookUseCase);
  }

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

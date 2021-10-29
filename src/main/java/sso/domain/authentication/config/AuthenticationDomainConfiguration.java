package sso.domain.authentication.config;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import sso.domain.authentication.core.usecase.AuthenticateWithFacebookUseCase;
import sso.domain.authentication.entrypoint.AuthenticateWithFacebookController;
import sso.domain.facebook.core.gateway.FacebookAuthenticationService;
import sso.domain.facebook.provider.FacebookAuthenticationWebService;

public class AuthenticationDomainConfiguration {

  @Provides
  @Singleton
  public FacebookAuthenticationService facebookAuthenticationService() {
    return new FacebookAuthenticationWebService();
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
}

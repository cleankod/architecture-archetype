package sso.domain.facebook.config;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import sso.domain.facebook.core.gateway.FacebookAuthenticationService;
import sso.domain.facebook.core.usecase.AuthenticateWithFacebookUseCase;
import sso.domain.facebook.entrypoint.AuthenticateWithFacebookController;
import sso.domain.facebook.provider.FacebookAuthenticationStubService;

public class FacebookConfiguration {
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
}

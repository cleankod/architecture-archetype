package sso.initialization;

import org.codejargon.feather.Feather;

import lombok.Getter;
import spark.servlet.SparkApplication;
import sso.domain.authentication.config.AuthenticationDomainConfiguration;
import sso.domain.authentication.entrypoint.AuthenticateWithUsernameAndPasswordController;
import sso.domain.facebook.config.FacebookConfiguration;
import sso.domain.facebook.entrypoint.AuthenticateWithFacebookController;
import sso.domain.user.config.UserDomainConfiguration;

@Getter
public class Application implements SparkApplication {

  private ContextHolder context;

  @Override
  public void init() {
    context = ContextHolder.CONTEXT;
  }

  private enum ContextHolder {
    CONTEXT;

    ContextHolder() {
      Feather feather = Feather.with(
          new AuthenticationDomainConfiguration(),
          new FacebookConfiguration(),
          new UserDomainConfiguration()
      );
      feather.instance(AuthenticateWithFacebookController.class);
      feather.instance(AuthenticateWithUsernameAndPasswordController.class);
    }
  }
}

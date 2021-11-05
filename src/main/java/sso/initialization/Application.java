package sso.initialization;

import org.codejargon.feather.Feather;

import lombok.Getter;
import spark.servlet.SparkApplication;
import sso.domain.authentication.config.AuthenticationDomainConfiguration;
import sso.domain.authentication.entrypoint.AuthenticateWithFacebookController;

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
          new AuthenticationDomainConfiguration()
      );
      feather.instance(AuthenticateWithFacebookController.class);
    }
  }
}

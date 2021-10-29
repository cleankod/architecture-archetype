package sso.domain.facebook.provider;

import java.util.Map;
import java.util.Optional;

import sso.domain.authentication.core.domain.AuthenticationFailedReason;
import sso.domain.facebook.core.domain.FacebookId;
import sso.domain.facebook.core.domain.FacebookName;
import sso.domain.facebook.core.domain.FacebookToken;
import sso.domain.facebook.core.gateway.FacebookAuthenticationService;
import sso.domain.facebook.provider.model.FacebookDatabaseEntry;
import sso.util.domain.Result;

public class FacebookAuthenticationStubService implements FacebookAuthenticationService {
  private static final Map<String, FacebookDatabaseEntry> USER_DATABASE = Map.of(
      "d80e08d6", FacebookDatabaseEntry.of("43cc8f2e-389d-11ec-b097-ef579a0a53dd", "John doe"),
      "14630188", FacebookDatabaseEntry.of("4888bb14-389d-11ec-bea0-c7563e55e7bc", "Alfredo Arachidi")
  );

  @Override
  public Result<FacebookName, AuthenticationFailedReason> authenticate(final FacebookId facebookId, final FacebookToken facebookToken) {
    return Optional.ofNullable(USER_DATABASE.get(facebookId.value()))
        .filter(facebookDatabaseEntry -> facebookDatabaseEntry.getToken().equals(facebookToken.value()))
        .map(facebookDatabaseEntry ->
            Result.<FacebookName, AuthenticationFailedReason>successful(FacebookName.of(facebookDatabaseEntry.getName()))
        )
        .orElseGet(() -> Result.fail(AuthenticationFailedReason.BAD_CREDENTIALS));
  }
}

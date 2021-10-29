package sso.domain.facebook.core.gateway;

import sso.domain.authentication.core.domain.AuthenticationFailedReason;
import sso.domain.facebook.core.domain.FacebookId;
import sso.domain.facebook.core.domain.FacebookName;
import sso.domain.facebook.core.domain.FacebookToken;
import sso.util.domain.Result;

public interface FacebookAuthenticationService {
  Result<FacebookName, AuthenticationFailedReason> authenticate(FacebookId facebookId, FacebookToken facebookToken);
}

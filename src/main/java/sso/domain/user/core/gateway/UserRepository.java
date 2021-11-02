package sso.domain.user.core.gateway;

import java.util.Optional;

import sso.domain.authentication.core.domain.HashedPassword;
import sso.domain.user.core.domain.User;
import sso.domain.user.core.domain.Username;

public interface UserRepository {
  Optional<User> find(Username username, HashedPassword hashedPassword);
}

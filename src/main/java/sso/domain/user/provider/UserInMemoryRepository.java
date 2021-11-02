package sso.domain.user.provider;

import java.util.Optional;
import java.util.Set;

import sso.domain.authentication.core.domain.HashedPassword;
import sso.domain.user.core.domain.User;
import sso.domain.user.core.domain.UserId;
import sso.domain.user.core.domain.Username;
import sso.domain.user.core.gateway.UserRepository;
import sso.domain.user.provider.model.UserDatabaseEntry;

public class UserInMemoryRepository implements UserRepository {
  private static final Set<UserDatabaseEntry> USERS = Set.of(
      new UserDatabaseEntry(123L, "bumblebee", "b03ddf3ca2e714a6548e7495e2a03f5e824eaac9837cd7f159c67b90fb4b7342"), //P@ssw0rd
      new UserDatabaseEntry(124L, "heatwave", "231ecc7d178da5f22983bc579599396d6c139a457987ae1ee0026d88432d6a72") //P@ssw0rd123!
  );

  @Override
  public Optional<User> find(final Username username, final HashedPassword hashedPassword) {
    return USERS.stream()
        .filter(userDatabaseEntry ->
            userDatabaseEntry.getUsername().equals(username.value()) && userDatabaseEntry.getHashedPassword().equals(hashedPassword.value())
        )
        .findFirst()
        .map(userDatabaseEntry ->
            User.builder()
                .userId(UserId.of(userDatabaseEntry.getId()))
                .username(Username.of(userDatabaseEntry.getUsername()))
                .build()
        );
  }
}

package sso.domain.user.provider.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDatabaseEntry {
  private final Long id;
  private final String username;
  private final String hashedPassword;
}

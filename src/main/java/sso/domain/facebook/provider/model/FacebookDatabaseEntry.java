package sso.domain.facebook.provider.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class FacebookDatabaseEntry {
  private final String token;
  private final String name;
}

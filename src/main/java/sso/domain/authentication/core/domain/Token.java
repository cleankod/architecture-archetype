package sso.domain.authentication.core.domain;

import java.util.UUID;

import sso.util.domain.ValueObject;

public class Token extends ValueObject<String> {
  protected Token(final String value) {
    super(value);
  }

  public static Token of(String value) {
    return new Token(value);
  }

  public static Token generate() {
    // TODO: Encapsulate and abstract:
    return Token.of(UUID.randomUUID().toString());
  }
}

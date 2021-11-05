package sso.domain.authentication.core.domain;

import sso.util.domain.ValueObject;

public class Token extends ValueObject<String> {
  protected Token(final String value) {
    super(value);
  }

  public static Token of(String value) {
    return new Token(value);
  }
}

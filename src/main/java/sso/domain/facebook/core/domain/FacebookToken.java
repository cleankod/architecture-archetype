package sso.domain.facebook.core.domain;

import sso.util.domain.ValueObject;

public class FacebookToken extends ValueObject<String> {
  protected FacebookToken(final String value) {
    super(value);
  }

  public static FacebookToken of(String value) {
    return new FacebookToken(value);
  }
}

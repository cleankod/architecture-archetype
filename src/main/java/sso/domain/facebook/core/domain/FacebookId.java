package sso.domain.facebook.core.domain;

import sso.util.domain.ValueObject;

public class FacebookId extends ValueObject<String> {
  private FacebookId(String value) {
    super(value);
  }

  public static FacebookId of(String value) {
    return new FacebookId(value);
  }
}

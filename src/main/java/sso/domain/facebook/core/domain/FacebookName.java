package sso.domain.facebook.core.domain;

import sso.util.domain.ValueObject;

public class FacebookName extends ValueObject<String> {
  protected FacebookName(final String value) {
    super(value);
  }

  public static FacebookName of(String name) {
    return new FacebookName(name);
  }
}

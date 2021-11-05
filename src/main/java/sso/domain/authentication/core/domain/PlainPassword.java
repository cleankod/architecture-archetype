package sso.domain.authentication.core.domain;

import java.nio.charset.StandardCharsets;

import sso.util.domain.ValueObject;

public class PlainPassword extends ValueObject<byte[]> {
  protected PlainPassword(final byte[] value) {
    super(value);
  }

  public static PlainPassword of(byte[] value) {
    return new PlainPassword(value);
  }

  public static PlainPassword of(String value) {
    return new PlainPassword(value.getBytes(StandardCharsets.UTF_8));
  }
}

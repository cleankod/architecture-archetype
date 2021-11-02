package sso.domain.authentication.core.domain;

import java.security.MessageDigest;

import lombok.SneakyThrows;
import sso.util.domain.ValueObject;
import sso.util.string.BytesToHexConverter;

public class HashedPassword extends ValueObject<String> {

  protected HashedPassword(final String value) {
    super(value);
  }

  public static HashedPassword of(String value) {
    return new HashedPassword(value);
  }

  @SneakyThrows
  public static HashedPassword of(PlainPassword plainPassword) {
    // TODO: Encapsulate and abstract:
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hashedValueBytes = digest.digest(plainPassword.value());
    String hashedValueString = BytesToHexConverter.convert(hashedValueBytes);
    return HashedPassword.of(hashedValueString);
  }
}

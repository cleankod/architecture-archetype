package sso.util.domain;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import sso.util.validation.Preconditions;

@EqualsAndHashCode
public class ValueObject<T> {
  private final T value;

  protected ValueObject(T value) {
    this.value = Preconditions.requireNonNull(value);
  }

  public T value() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  public static <V, O extends ValueObject<V>> V unwrapOrNull(O valueObject) {
    return Optional.ofNullable(valueObject)
        .map(ValueObject::value)
        .orElse(null);
  }
}

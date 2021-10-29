package sso.util.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Preconditions<E> {
    private final E value;
    private final Map<Predicate<E>, ValidationError> predicates = new LinkedHashMap<>();

    private Preconditions(E value) {
        this.value = value;
    }

    public static <T> Preconditions<T> from(T t) {
        return new Preconditions<>(t);
    }

    public static <T> T requireNonNull(T value) {
        if (value == null)
            throw new ValidationException(ValidationError.NULL);
        return value;
    }

    public Preconditions<E> add(Predicate<E> predicate, ValidationError error) {
        this.predicates.put(predicate, error);
        return this;
    }

    public E run() {
        predicates.entrySet().stream()
                .filter(predicate -> predicate.getKey().test(value))
                .findFirst()
                .ifPresent(predicate -> {
                    throw new ValidationException(predicate.getValue());
                });

        return value;
    }
}

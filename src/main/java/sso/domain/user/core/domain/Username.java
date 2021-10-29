package sso.domain.user.core.domain;

import sso.util.domain.ValueObject;

public class Username extends ValueObject<String> {
    private Username(String value) {
        super(value);
    }

    public static Username of(String value) {
        return new Username(value);
    }
}

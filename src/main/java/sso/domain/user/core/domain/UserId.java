package sso.domain.user.core.domain;


import sso.util.domain.ValueObject;

public class UserId extends ValueObject<Long> {
    private UserId(long value) {
        super(value);
    }

    public static UserId of(long value) {
        return new UserId(value);
    }
}

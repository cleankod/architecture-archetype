package sso.domain.authentication.core.domain;

import lombok.Builder;
import lombok.Getter;
import sso.domain.user.core.domain.User;
import sso.util.validation.Preconditions;

@Getter
public class Authentication {
    private final Token token;
    private final User user;
    private final boolean isPasswordChangeRequired;

    @Builder
    private Authentication(Token token, User user, boolean isPasswordChangeRequired) {
        this.token = Preconditions.requireNonNull(token);
        this.user = Preconditions.requireNonNull(user);
        this.isPasswordChangeRequired = isPasswordChangeRequired;
    }
}

package sso.domain.user.core.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class User {
    private final UserId userId;
    private final Username username;
}

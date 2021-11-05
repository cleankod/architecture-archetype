package sso.util.validation;

import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private final ValidationError validationError;
    ValidationException(ValidationError validationError) {
        super(validationError.name());
        this.validationError = validationError;
    }
}

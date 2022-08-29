package com.api.administration.models.errors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
    private String code;
    private String message;

    public ValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

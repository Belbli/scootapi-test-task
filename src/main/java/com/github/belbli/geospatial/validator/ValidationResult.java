package com.github.belbli.geospatial.validator;

public record ValidationResult(
        boolean isValid,
        String errorMessage
) {
    public String errorMessage() {
        return isValid ? "" : errorMessage;
    }
}

package com.github.belbli.geospatial.validator;

@FunctionalInterface
public interface Validator<T> {
    ValidationResult validate(T t);

    default Validator<T> and(Validator<? super T> other) {
        return obj -> {
            ValidationResult result = this.validate(obj);
            return !result.isValid() ? result : other.validate(obj);
        };
    }
}

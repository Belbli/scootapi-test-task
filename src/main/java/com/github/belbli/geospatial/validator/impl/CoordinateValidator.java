package com.github.belbli.geospatial.validator.impl;

import com.github.belbli.geospatial.exception.ValidationException;
import com.github.belbli.geospatial.model.Coordinate;
import com.github.belbli.geospatial.validator.ValidationResult;
import com.github.belbli.geospatial.validator.Validator;

import java.util.Objects;

public class CoordinateValidator {
    private static final Validator<Coordinate> nonNullValidator = coordinate -> {
        boolean nonNull = Objects.nonNull(coordinate);
        boolean latitudeNonNull = false;
        boolean longitudeNonNull = false;
        if (nonNull) {
            latitudeNonNull = Objects.nonNull(coordinate.latitude());
            longitudeNonNull = Objects.nonNull(coordinate.longitude());
        }
        return new ValidationResult(nonNull && latitudeNonNull && longitudeNonNull, "Coordinate, latitude and longitude must not be null.");
    };

    private static final Validator<Coordinate> latitudeValidator = coordinate -> {
        return new ValidationResult(Math.abs(coordinate.latitude()) <= 90, "latitude acceptable range is [-90; 90]");
    };

    private static final Validator<Coordinate> longitudeValidator = coordinate -> {
        return new ValidationResult(Math.abs(coordinate.longitude()) <= 180, "longitude acceptable range is [-90; 90]");
    };

    private static final Validator<Coordinate> coordinateValidator = nonNullValidator.and(latitudeValidator).and(longitudeValidator);


    public static void validate(Coordinate coordinate) {
        ValidationResult result = coordinateValidator.validate(coordinate);
        if (!result.isValid()) {
            throw new ValidationException(result.errorMessage());
        }
    }
}

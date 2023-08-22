package com.github.belbli.geospatial.model.request;

import com.github.belbli.geospatial.model.Coordinate;
import com.github.belbli.geospatial.model.DistanceUnit;

public record DistanceCalculationRequest(
        Coordinate firstCoordinate,
        Coordinate secondCoordinate,
        DistanceUnit distanceUnit
) {
}

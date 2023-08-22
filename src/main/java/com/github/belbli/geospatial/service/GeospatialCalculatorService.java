package com.github.belbli.geospatial.service;

import com.github.belbli.geospatial.model.Coordinate;
import com.github.belbli.geospatial.model.Distance;
import com.github.belbli.geospatial.model.DistanceUnit;

public interface GeospatialCalculatorService {
    Distance getDistance(Coordinate coordinate1, Coordinate coordinate2, DistanceUnit distanceUnit);
}

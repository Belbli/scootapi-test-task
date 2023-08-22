package com.github.belbli.geospatial.service.impl;

import com.github.belbli.geospatial.model.Coordinate;
import com.github.belbli.geospatial.model.Distance;
import com.github.belbli.geospatial.model.DistanceUnit;
import com.github.belbli.geospatial.service.GeospatialCalculatorService;
import com.github.belbli.geospatial.validator.impl.CoordinateValidator;
import org.springframework.stereotype.Service;

@Service
public class DefaultGeospatialCalculatorService implements GeospatialCalculatorService {
    private static final double EARTH_RADIUS_METERS = 6378.137 * 1000;
    @Override
    public Distance getDistance(Coordinate c1, Coordinate c2, DistanceUnit distanceUnit) {
        CoordinateValidator.validate(c1);
        CoordinateValidator.validate(c2);
        if (c1.equals(c2)) {
            return new Distance(0.0, distanceUnit);
        } else {
            double theta = c1.longitude() - c2.longitude();
            double dist = getDistance(c1, c2, theta);
            double distInMeters = Math.toDegrees(Math.acos(dist)) * Math.PI * EARTH_RADIUS_METERS / 180;

            return new Distance(distInMeters * distanceUnit.metersToUnitFactor, distanceUnit);
        }
    }

    private static double getDistance(Coordinate c1, Coordinate c2, double theta) {
        return Math.sin(Math.toRadians(c1.latitude())) * Math.sin(Math.toRadians(c2.latitude()))
                + Math.cos(Math.toRadians(c1.latitude())) * Math.cos(Math.toRadians(c2.latitude())) * Math.cos(Math.toRadians(theta));
    }

}

package com.github.belbli.geospatial.service.impl;


import com.github.belbli.geospatial.exception.ValidationException;
import com.github.belbli.geospatial.model.Coordinate;
import com.github.belbli.geospatial.model.Distance;
import com.github.belbli.geospatial.model.DistanceUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

import static org.testng.Assert.assertEquals;

public class DefaultGeospatialCalculatorServiceTest {
    private final DefaultGeospatialCalculatorService geospatialCalculatorService = new DefaultGeospatialCalculatorService();

    @Test
    public void testIfSameCoordinates() {
        Distance distance = geospatialCalculatorService
                .getDistance(new Coordinate(1.0, 1.0), new Coordinate(1.0, 1.0), DistanceUnit.METER);

        assertEquals(distance.distance(), 0.0);
        assertEquals(distance.unit(), DistanceUnit.METER);
    }

    @Test
    public void testIfCalculationsAreRight() {
        DistanceUnit distanceUnit = DistanceUnit.KILOMETER;
        Distance distance = geospatialCalculatorService
                .getDistance(new Coordinate(35.6544, 139.74477), new Coordinate(21.4225, 39.8261), distanceUnit);
        DecimalFormat df = new DecimalFormat("#.##");
        assertEquals(Double.valueOf(df.format(distance.distance())), 9491.28);
        assertEquals(distance.unit(), distanceUnit);
    }

    @Test(dataProvider = "badCoordinatesDataProvider", expectedExceptions = {ValidationException.class})
    public void testIfLatitudeIsOutOfRange(Coordinate c1, Coordinate c2) {
        geospatialCalculatorService
                .getDistance(c1, c2, DistanceUnit.METER);
    }

    @DataProvider(name = "badCoordinatesDataProvider")
    private Object[][] badCoordinatesDataProvider() {
        return new Object[][] {
                {new Coordinate(-91.0, 1.0), new Coordinate(1.0, 1.0)},
                {new Coordinate(-1.0, 181.0), new Coordinate(1.0, 1.0)},
                {new Coordinate(1.0, 1.0), new Coordinate(-1.0, 181.0)},
                {null, new Coordinate(1.0, 1.0)},
                {new Coordinate(1.0, 1.0), null},
        };
    }

}
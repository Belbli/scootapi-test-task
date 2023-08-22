package com.github.belbli.geospatial.model;

public enum DistanceUnit {
    METER(1.0),
    KILOMETER(0.001),
    MILE(0.0006213712);

    public final Double metersToUnitFactor;

    DistanceUnit(Double metersToUnitFactor) {
        this.metersToUnitFactor = metersToUnitFactor;
    }
}

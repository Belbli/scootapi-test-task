package com.github.belbli.geospatial.controller;

import com.github.belbli.geospatial.model.Distance;
import com.github.belbli.geospatial.model.request.DistanceCalculationRequest;
import com.github.belbli.geospatial.service.GeospatialCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coordinates")
public class GeospatialCalculatorRestController {

    private final GeospatialCalculatorService geospatialCalculatorService;

    @Autowired
    public GeospatialCalculatorRestController(GeospatialCalculatorService geospatialCalculatorService) {
        this.geospatialCalculatorService = geospatialCalculatorService;
    }

    @PostMapping("/distance")
    public Distance getDistance(@RequestBody DistanceCalculationRequest distanceCalculationRequest) {
        return geospatialCalculatorService
                .getDistance(
                        distanceCalculationRequest.firstCoordinate(),
                        distanceCalculationRequest.secondCoordinate(),
                        distanceCalculationRequest.distanceUnit()
                );
    }
}

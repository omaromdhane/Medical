package com.sayfr.deviceservice.domain;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;

@Document(collection = "Sensors")
public class Sensor {

    @Id
    private final SensorId sensorId;
    
    private final String name;

    private final String measurementUnit;

    @Getter
    private ValueType readingType;

    public Sensor(String name, SensorId sensorId, String measurementUnit, ValueType readingType) {
        this.name = name;
        this.sensorId = sensorId;
        this.measurementUnit = measurementUnit;
        this.readingType = readingType;
    }

    public String getName() {
        return name;
    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public SensorReading generateReading(Object value, Instant timestamp) {
        try {
            return new SensorReading(value, timestamp);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

}

package com.sayfr.deviceservice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "Devices")
public class Device {

    @Id
    @Indexed(unique = true)
    private final DeviceId deviceId;

    private final String name;

    private List<SensorId> sensorIds = new ArrayList<>();

    private DeviceConfiguration configuration;

    private Mapper mapper;

    public Device(DeviceId deviceId, String name, List<SensorId> sensorIds, DeviceConfiguration deviceConfiguration, Mapper mapper) {
        this.deviceId = deviceId;
        this.name = name;
        this.sensorIds = sensorIds;
        this.configuration = deviceConfiguration;
        this.mapper = mapper;
    }

    public boolean hasSensors() {
        return !sensorIds.isEmpty();
    }
    
}


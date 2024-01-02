package com.sayfr.deviceservice.services;

import java.util.List;
import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceConfiguration;
import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.domain.Mapper;
import com.sayfr.deviceservice.domain.Sensor;
import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.ValueType;

public interface DevicesManger {
    public Sensor createSensor(String name, String measurementUnit, ValueType valueType);
    public Device createDevice(String name,List<SensorId> sensorIds, DeviceConfiguration configuration, Mapper mapper);
    public Device getDeviceById(DeviceId deviceId);
}

package com.sayfr.deviceservice.data_access;

import java.util.List;

import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.domain.Sensor;
import com.sayfr.deviceservice.domain.SensorId;

public interface SensorDao {
    void saveSensor(Sensor sensor);
    Sensor getSensorById(SensorId sensorId);
    List<Sensor> getSensorsByDeviceId(DeviceId deviceId);
    void updateSensor(Sensor sensor);
    void deleteSensor(SensorId sensorId);
    boolean existsById(SensorId sensorId);
}

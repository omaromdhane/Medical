package com.sayfr.deviceservice.services;

import java.util.List;
import com.sayfr.deviceservice.data_access.DeviceDao;
import com.sayfr.deviceservice.data_access.SensorDao;
import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceConfiguration;
import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.domain.Mapper;
import com.sayfr.deviceservice.domain.Sensor;
import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.ValueType;

public class DevicesManagerImpl implements DevicesManger{
    
    private DeviceDao deviceDao;
    public SensorDao sensorDao;

    public DevicesManagerImpl(DeviceDao deviceDao, SensorDao sensorDao) {
        this.deviceDao = deviceDao;
        this.sensorDao = sensorDao;
    }
    
    @Override
    public Device createDevice(String name,List<SensorId> sensorIds, DeviceConfiguration configuration, Mapper mapper) {
        if(sensorIds.isEmpty()){
            return null;
        }
        for (SensorId sensorId : sensorIds) {
            if(!sensorDao.existsById(sensorId)){
                return null;
            }
        }
        
        Device newDevice = new Device(new DeviceId(), name, sensorIds, configuration, mapper);
        deviceDao.saveDevice(newDevice);
        return newDevice;
    }

    @Override
    public Sensor createSensor(String name, String measurementUnit, ValueType valueType) {
        Sensor newSensor = new Sensor(name, new SensorId(), measurementUnit, valueType);
        sensorDao.saveSensor(newSensor);
        return newSensor;
    }

    @Override
    public Device getDeviceById(DeviceId deviceId) {
        return deviceDao.getDeviceById(deviceId);
    }
}

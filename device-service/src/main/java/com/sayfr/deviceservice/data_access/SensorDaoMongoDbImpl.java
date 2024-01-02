package com.sayfr.deviceservice.data_access;

import java.util.List;

import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.domain.Sensor;
import com.sayfr.deviceservice.domain.SensorId;

public class SensorDaoMongoDbImpl implements SensorDao{

    private SensorRepository sensorRepository;


    public SensorDaoMongoDbImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    @Override
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }


    @Override
    public Sensor getSensorById(SensorId sensorId) {
        return sensorRepository.findById(sensorId).orElse(null);
    }


    @Override
    public List<Sensor> getSensorsByDeviceId(DeviceId deviceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSensorsByDeviceId'");
    }


    @Override
    public void updateSensor(Sensor sensor) {
        sensorRepository.save(null);
    }


    @Override
    public void deleteSensor(SensorId sensorId) {
        sensorRepository.deleteById(sensorId);
    }


    @Override
    public boolean existsById(SensorId sensorId) {
        return sensorRepository.existsById(sensorId);
    }

    

}

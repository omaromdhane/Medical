package com.sayfr.deviceservice.data_access;

import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceId;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DeviceDaoMongoDbImpl implements DeviceDao {

    private final DeviceRepository deviceRepository;

    public DeviceDaoMongoDbImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public Device getDeviceById(DeviceId deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public void updateDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(DeviceId deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    @Override
    public boolean existsById(DeviceId deviceId) {
        return deviceRepository.existsById(deviceId);
    }
}


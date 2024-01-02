package com.sayfr.deviceservice.data_access;

import java.util.List;
import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceId;

public interface DeviceDao {
    void saveDevice(Device device);
    Device getDeviceById(DeviceId deviceId);
    List<Device> getAllDevices();
    void updateDevice(Device device);
    void deleteDevice(DeviceId deviceId);
    boolean existsById(DeviceId deviceId);
}

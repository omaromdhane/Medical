package com.sayfr.deviceservice.services;

import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.domain.Mapper;

public interface DataReceiver {
    public void receiveData(DeviceId deviceId, Object data);
    public void addDevice(DeviceId deviceId, Mapper mapper);
}

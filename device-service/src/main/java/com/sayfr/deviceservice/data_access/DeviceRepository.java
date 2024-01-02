package com.sayfr.deviceservice.data_access;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceId;

public interface DeviceRepository extends MongoRepository<Device, DeviceId> {
    
}

package com.sayfr.deviceservice.data_access;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sayfr.deviceservice.domain.Sensor;
import com.sayfr.deviceservice.domain.SensorId;

public interface SensorRepository extends MongoRepository<Sensor,SensorId>{
    
}

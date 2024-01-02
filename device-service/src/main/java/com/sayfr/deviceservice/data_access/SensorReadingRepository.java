package com.sayfr.deviceservice.data_access;

import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sayfr.deviceservice.domain.RecordId;
import com.sayfr.deviceservice.domain.SensorReading;

public interface SensorReadingRepository extends MongoRepository<SensorReading, String>{

    List<SensorReading> findByTimestampBetweenAndRecordId(Instant startTime, Instant endTime,
            RecordId recordId);
    
}

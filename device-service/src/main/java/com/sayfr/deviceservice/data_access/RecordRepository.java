package com.sayfr.deviceservice.data_access;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sayfr.deviceservice.domain.Record;
import com.sayfr.deviceservice.domain.RecordId;

public interface RecordRepository extends MongoRepository<Record,RecordId>{
    
}

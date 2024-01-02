package com.sayfr.deviceservice.data_access;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sayfr.deviceservice.domain.Patient;
import com.sayfr.deviceservice.domain.PatientId;

public interface PatientRepository extends MongoRepository<Patient,PatientId>{
    
}

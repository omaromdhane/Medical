package com.sayfr.deviceservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document("Patients")
public class Patient {

    @Id
    @Getter
    private PatientId patientId;

    public Patient(PatientId patientId){
        this.patientId = patientId;
    }
    
}

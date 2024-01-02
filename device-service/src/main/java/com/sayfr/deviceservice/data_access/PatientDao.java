package com.sayfr.deviceservice.data_access;

import java.util.List;

import com.sayfr.deviceservice.domain.Patient;
import com.sayfr.deviceservice.domain.PatientId;

public interface PatientDao {

    void savePatient(Patient patient);

    Patient getPatientById(PatientId patientId);

    List<Patient> getAllPatients();

    void updatePatient(Patient patient);

    void deletePatient(PatientId patientId);

    boolean existsById(PatientId patientId);
}


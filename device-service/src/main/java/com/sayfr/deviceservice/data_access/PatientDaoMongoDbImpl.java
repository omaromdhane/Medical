package com.sayfr.deviceservice.data_access;

import java.util.List;

import com.sayfr.deviceservice.domain.Patient;
import com.sayfr.deviceservice.domain.PatientId;

public class PatientDaoMongoDbImpl implements PatientDao{

    private final PatientRepository patientRepository;

    public PatientDaoMongoDbImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void savePatient(Patient patient) {
       patientRepository.save(patient);
    }

    public Patient getPatientById(PatientId patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void deletePatient(PatientId patientId) {
        patientRepository.deleteById(patientId);;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public boolean existsById(PatientId patientId) {
        return patientRepository.existsById(patientId);
    }
}

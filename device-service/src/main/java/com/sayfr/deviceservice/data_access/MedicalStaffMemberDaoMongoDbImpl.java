package com.sayfr.deviceservice.data_access;

import java.util.List;

import com.sayfr.deviceservice.domain.MedicalStaffMemberId;
import com.sayfr.deviceservice.domain.MedicalStaffMember;

public class MedicalStaffMemberDaoMongoDbImpl implements MedicalStaffMemberDao{

    MedicalStaffMemberRepository medicalStaffMemberRepository;

    public MedicalStaffMemberDaoMongoDbImpl(MedicalStaffMemberRepository medicalStaffMemberRepository) {
        this.medicalStaffMemberRepository = medicalStaffMemberRepository;
    }
    

    @Override
    public void saveMedicalStaffMember(MedicalStaffMember medicalStaffMember) {
        medicalStaffMemberRepository.save(medicalStaffMember);
    }

    @Override
    public MedicalStaffMember findMedicalStaffMemberById(MedicalStaffMemberId id) {
        return medicalStaffMemberRepository.findById(id).orElse(null);
    }

    @Override
    public List<MedicalStaffMember> findAllMedicalStaffMembers() {
        return medicalStaffMemberRepository.findAll();
    }

    @Override
    public void updateMedicalStaffMember(MedicalStaffMember medicalStaffMember) {
        medicalStaffMemberRepository.save(medicalStaffMember);
    }

    @Override
    public void deleteMedicalStaffMember(MedicalStaffMemberId id) {
        medicalStaffMemberRepository.deleteById(id);
    }


    @Override
    public boolean existsById(MedicalStaffMemberId id) {
        return medicalStaffMemberRepository.existsById(id);
    }
    
}

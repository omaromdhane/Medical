package com.sayfr.deviceservice.data_access;

import java.util.List;

import com.sayfr.deviceservice.domain.MedicalStaffMemberId;
import com.sayfr.deviceservice.domain.MedicalStaffMember;

public interface MedicalStaffMemberDao {
    void saveMedicalStaffMember(MedicalStaffMember medicalStaffMember);
    MedicalStaffMember findMedicalStaffMemberById(MedicalStaffMemberId id);
    List<MedicalStaffMember> findAllMedicalStaffMembers();
    void updateMedicalStaffMember(MedicalStaffMember medicalStaffMember);
    void deleteMedicalStaffMember(MedicalStaffMemberId id);
    boolean existsById(MedicalStaffMemberId id);
}

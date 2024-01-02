package com.sayfr.deviceservice.data_access;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sayfr.deviceservice.domain.MedicalStaffMemberId;
import com.sayfr.deviceservice.domain.MedicalStaffMember;

public interface MedicalStaffMemberRepository extends MongoRepository<MedicalStaffMember,MedicalStaffMemberId>{
    
}

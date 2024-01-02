package com.sayfr.deviceservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;

@Document(collection = "MedicalStaff")
public class MedicalStaffMember {

    @Getter
    @Id
    @Indexed(unique = true)
    private MedicalStaffMemberId medicalStaffMemberId;

    public MedicalStaffMember(MedicalStaffMemberId medicalStaffMemberId) {
        this.medicalStaffMemberId = medicalStaffMemberId;
    }
    
}

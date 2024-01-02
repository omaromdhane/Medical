package com.sayfr.deviceservice.domain;

import java.util.UUID;
import org.springframework.data.annotation.PersistenceCreator;

public class MedicalStaffMemberId {

    private final String uuid;

    public MedicalStaffMemberId() {
        this.uuid = UUID.randomUUID().toString();
    }

    public MedicalStaffMemberId(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.uuid = uuid.toString();
    }

    @PersistenceCreator
    private MedicalStaffMemberId(String uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalStaffMemberId MedicalStaffMemberId = (MedicalStaffMemberId) o;
        return uuid.equals(MedicalStaffMemberId.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

}

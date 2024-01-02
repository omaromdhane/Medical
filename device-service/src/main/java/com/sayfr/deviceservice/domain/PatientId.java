package com.sayfr.deviceservice.domain;

import java.util.UUID;
import org.springframework.data.annotation.PersistenceCreator;

public class PatientId {

    private final String uuid;

    public PatientId() {
        this.uuid = UUID.randomUUID().toString();
    }

    public PatientId(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.uuid = uuid.toString();
    }

    @PersistenceCreator
    private PatientId(String uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientId PatientId = (PatientId) o;
        return uuid.equals(PatientId.uuid);
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

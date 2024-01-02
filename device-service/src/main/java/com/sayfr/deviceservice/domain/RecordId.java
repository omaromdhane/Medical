package com.sayfr.deviceservice.domain;

import java.util.UUID;
import org.springframework.data.annotation.PersistenceCreator;

public class RecordId {

    private final String uuid;

    public RecordId() {
        this.uuid = UUID.randomUUID().toString();
    }

    public RecordId(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.uuid = uuid.toString();
    }

    @PersistenceCreator
    private RecordId(String uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordId RecordId = (RecordId) o;
        return uuid.equals(RecordId.uuid);
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
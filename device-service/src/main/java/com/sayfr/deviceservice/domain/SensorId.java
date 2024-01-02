package com.sayfr.deviceservice.domain;

import java.util.UUID;
import org.springframework.data.annotation.PersistenceCreator;

public class SensorId {
    
    private final String uuid;

    public SensorId() {
        this.uuid = UUID.randomUUID().toString();
    }

    public SensorId(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.uuid = uuid.toString();
    }

    @PersistenceCreator
    private SensorId(String uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorId SensorId = (SensorId) o;
        return uuid.equals(SensorId.uuid);
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

package com.sayfr.deviceservice.domain;

import java.util.UUID;
import org.springframework.data.annotation.PersistenceCreator;

public class DeviceId {

    private final String uuid;

    public DeviceId() {
        this.uuid = UUID.randomUUID().toString();
    }

    public DeviceId(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.uuid = uuid.toString();
    }

    @PersistenceCreator
    private DeviceId(String uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceId DeviceId = (DeviceId) o;
        return uuid.equals(DeviceId.uuid);
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

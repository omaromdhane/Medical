package com.sayfr.deviceservice.domain;

import java.util.Map;

public interface Mapper {
    public Map<SensorId, SensorReading> getReadings(Object input);
}

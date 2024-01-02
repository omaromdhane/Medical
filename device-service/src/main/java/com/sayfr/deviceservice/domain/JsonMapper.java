package com.sayfr.deviceservice.domain;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonMapper implements Mapper {

    private List<SensorId> sensorIds = new ArrayList<>();
    private List<String> telemetryFields = new ArrayList<>();
    private String timestampMapping = "timestamp";

    public JsonMapper(Map<SensorId, String> telemetryMapping, String timestampMapping) {
       for (Map.Entry<SensorId, String> entry : telemetryMapping.entrySet()) {
            sensorIds.add(entry.getKey());
            telemetryFields.add(entry.getValue());
        }
        this.timestampMapping = timestampMapping;
    }

    @Override
    public Map<SensorId, SensorReading> getReadings(Object input) {
        if (!(input instanceof String)) {
            throw new IllegalArgumentException("Input must be a JSON string.");
        }

        String jsonInput = (String) input;
        Object obj = JSONValue.parse(jsonInput);
        
        if (!(obj instanceof JSONObject)) {
            throw new IllegalArgumentException("Input JSON is not a valid object.");
        }
        
        JSONObject jsonObject = (JSONObject) obj;
        
        String timestamp = (String) jsonObject.get(timestampMapping);
        Instant timeInstant = null;
        
        if (timestamp != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            try {
                Instant instant = Instant.from(formatter.parse(timestamp));
                timeInstant = instant;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            timeInstant = Instant.now();
        }

        Map<SensorId, SensorReading> readings = new HashMap<>();

        for (int i = 0; i< sensorIds.size();i++) {
            SensorId sensorId = sensorIds.get(i);
            String field = telemetryFields.get(i);
            Object value = jsonObject.get(field);
            if (value != null) {
                readings.put(sensorId, new SensorReading(value, timeInstant));
            }
        }

        return readings;
    }
}

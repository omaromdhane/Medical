package com.sayfr.deviceservice.domain;

import java.time.Instant;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TimeSeries;
import org.springframework.data.mongodb.core.timeseries.Granularity;

@Document(collection = "Readings")
@TimeSeries(timeField = "timestamp", granularity = Granularity.SECONDS)
public class SensorReading {
    
    private final Object value;
    
    @Field("timestamp")
    private final Instant timestamp;

    @Field("record")
    private RecordId recordId;

    public RecordId getRecordId() {
        return this.recordId;
    }

    public void setRecordId(RecordId record) {
        this.recordId = record;
    }

    public SensorReading(Object value) {
        this(value, Instant.now());
    }

    public SensorReading(Object value, Instant timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    @PersistenceCreator
    private SensorReading(Object value, Instant timestamp, RecordId recordId) {
        this.value = value;
        this.timestamp = timestamp;
        this.recordId = recordId;
    }

    public Object getValue() {
        return value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}

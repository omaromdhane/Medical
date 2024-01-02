package com.sayfr.deviceservice.domain;

import java.time.Instant;
import lombok.Getter;

@Getter
public class RecordSegment {
    private RecordId recordId;
    private SensorReadingBatch data;
    private Instant startInstant;    
    private Instant endInstant;


    public RecordSegment(RecordId recordId, SensorReadingBatch data, Instant startInstant, Instant endInstant) {
        this.recordId = recordId;
        this.data = data;
        this.startInstant = startInstant;
        this.endInstant = endInstant;
    }

    @Override
    public String toString() {
        return "{" +
            " recordId='" + getRecordId() + "'" +
            ", data='" + getData() + "'" +
            ", startInstant='" + getStartInstant() + "'" +
            ", endInstant='" + getEndInstant() + "'" +
            "}";
    }

}

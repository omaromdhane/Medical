package com.sayfr.deviceservice.data_access;

import java.time.Instant;
import java.util.List;
import com.sayfr.deviceservice.domain.Record;
import com.sayfr.deviceservice.domain.RecordId;
import com.sayfr.deviceservice.domain.RecordSegment;
import com.sayfr.deviceservice.domain.SensorReading;
import com.sayfr.deviceservice.domain.SensorReadingBatch;

public interface RecordDao {
    public Record saveRecord(Record record);
    public List<Record> getAllRecords();
    public Record getRecordById(RecordId recordId);
    public void deleteRecordById(RecordId recordId);
    public void saveReading(RecordId recordId, SensorReading reading);
    public void saveReadings(RecordId recordId, SensorReadingBatch readings);
    public boolean existsById(RecordId recordId);
    public RecordSegment getRecordSegment(Instant startTime, Instant endTime, RecordId recordId);
}

package com.sayfr.deviceservice.data_access;

import java.time.Instant;
import java.util.List;

import com.sayfr.deviceservice.domain.Record;
import com.sayfr.deviceservice.domain.RecordId;
import com.sayfr.deviceservice.domain.RecordSegment;
import com.sayfr.deviceservice.domain.SensorReading;
import com.sayfr.deviceservice.domain.SensorReadingBatch;

public class RecordDaoMongoDbImpl implements RecordDao{

    private final SensorReadingRepository sensorReadingRepository;
    private final RecordRepository recordRepository;

    public RecordDaoMongoDbImpl(SensorReadingRepository sensorReadingRepository, RecordRepository recordRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record getRecordById(RecordId recordId) {
        return recordRepository.findById(recordId).orElse(null);
    }

    @Override
    public void deleteRecordById(RecordId recordId) {
        recordRepository.deleteById(recordId);;
    }

    @Override
    public void saveReading(RecordId recordId, SensorReading reading) {
        reading.setRecordId(recordId);
        sensorReadingRepository.save(reading);
    }

    @Override
    public void saveReadings(RecordId recordId, SensorReadingBatch readings) {
        for (SensorReading sensorReading : readings.getData()) {
            saveReading(recordId, sensorReading);
        };
    }

    @Override
    public boolean existsById(RecordId recordId) {
        return recordRepository.existsById(recordId);
    }

    @Override
    public RecordSegment getRecordSegment(Instant startTime, Instant endTime, RecordId recordId) {
        List<SensorReading> sensorReadings = sensorReadingRepository.findByTimestampBetweenAndRecordId(startTime, endTime, recordId);
        SensorReadingBatch readingBatch = new SensorReadingBatch(sensorReadings);
        RecordSegment recordSegment = new RecordSegment(recordId, readingBatch, startTime, endTime);
        return recordSegment;
    }

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

}


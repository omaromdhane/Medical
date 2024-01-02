package com.sayfr.deviceservice.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.sayfr.deviceservice.data_access.RecordDao;
import com.sayfr.deviceservice.domain.PatientId;
import com.sayfr.deviceservice.domain.Record;
import com.sayfr.deviceservice.domain.RecordId;
import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.SensorReading;
import com.sayfr.deviceservice.domain.SensorReadingBatch;
import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.MedicalStaffMemberId;

public class Recorder implements ReadingSubscriber {

    RecordDao recordDao;

    DataRouter dataRouter;

    private Map<SensorId, SensorRecorder> recordedDataMap;

    public Recorder(RecordDao recordDao, DataRouter dataRouter) {
        this.recordedDataMap = new HashMap<>();
        this.recordDao = recordDao;
        this.dataRouter = dataRouter;
    }

    public void startRecording(SensorId sensorId, PatientId patientId, MedicalStaffMemberId staffId, Date startingDate) {
        if (!recordedDataMap.containsKey(sensorId)) {
            Record newRecord = new Record(new RecordId(), patientId, sensorId,staffId);
            recordedDataMap.putIfAbsent(sensorId, new SensorRecorder(recordDao, newRecord));
            dataRouter.subscribeOnReadings(this,sensorId);
        }
    }

    public void startRecording(SensorId sensorId, PatientId patientId, MedicalStaffMemberId staffId) {
        startRecording(sensorId, patientId, staffId,new Date());
    }

    public void startRecording(Device device, PatientId patientId, MedicalStaffMemberId staffId) {
        Date newDate = new Date();
        for (SensorId sensorId : device.getSensorIds()) {
            startRecording(sensorId, patientId, staffId, newDate);
        }
    }

    public void stopRecording(SensorId sensorId) {
        if (recordedDataMap.containsKey(sensorId)) {
            dataRouter.unsubscribeOnAllReadings(this);
            recordedDataMap.get(sensorId).stopRecording();
            recordedDataMap.remove(sensorId);
        }
    }

    public void stopRecording(Device device) {
        for (SensorId sensorId : device.getSensorIds()) {
            stopRecording(sensorId);
        }
    }


    public void receiveData(SensorId sensorId, SensorReadingBatch sensorReadingBatch) {
        if (!recordedDataMap.containsKey(sensorId) || sensorReadingBatch.isEmpty()) {
            return;
        }
        SensorRecorder sensorRecorder = recordedDataMap.get(sensorId);
        for (SensorReading sensorReading : sensorReadingBatch.getDataAsSortedSet()) {
            sensorRecorder.receiveData(sensorReading);
        }
    }

    public void cancelRecording(SensorId sensorId) {
        if (recordedDataMap.containsKey(sensorId)) {
            recordedDataMap.get(sensorId).cancelRecording();
            recordedDataMap.remove(sensorId);
        }
    }

}


class SensorRecorder{

    private Record record;

    private RecordDao recordDao;

    public SensorRecorder(RecordDao recordDao, Record record) {
        this.record = record;
        this.recordDao = recordDao;
        recordDao.saveRecord(record);
    }

    public void receiveData(SensorReading sensorReading){
        recordDao.saveReading(record.getRecordId(), sensorReading);
    }

    public void cancelRecording(){
        record.setEndDate(new Date());
        recordDao.saveRecord(record);
    };

    public void stopRecording(){
        record.setEndDate(new Date());
        recordDao.saveRecord(record);
    }
}
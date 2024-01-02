package com.sayfr.deviceservice.domain;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a recorded data entry for a patient from a specific device and sensor.
 */
@Getter
@Setter
@Document("Records")
public class Record {

    @Id
    private final RecordId recordId;
    
    private final PatientId patientId;
    
    private final SensorId sensorId;

    private final MedicalStaffMemberId staffId;

    private Date startDate;

    private Date endDate;

    public Record(RecordId recordId, PatientId patientId, SensorId sensorId, MedicalStaffMemberId staffId) {
        this(recordId,patientId,sensorId,staffId,new Date());
    }

    public Record(RecordId recordId, PatientId patientId, SensorId sensorId, MedicalStaffMemberId staffId, Date startDate){
        this.patientId = patientId;
        this.sensorId = sensorId;
        this.recordId = recordId;
        this.staffId = staffId;
        this.startDate = startDate;
    }

    @PersistenceCreator
    private Record(RecordId recordId, PatientId patientId, SensorId sensorId, MedicalStaffMemberId staffId, Date startDate, Date endDate) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.sensorId = sensorId;
        this.staffId = staffId;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public PatientId getPatientId() {
        return patientId;
    }

    public SensorId getSensorId() {
        return sensorId;
    }


    @Override
    public String toString() {
        return "{" +
            " recordId='" + getRecordId() + "'" +
            ", patientId='" + getPatientId() + "'" +
            ", sensorId='" + getSensorId() + "'" +
            ", staffId='" + getStaffId() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
    

}

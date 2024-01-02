package com.sayfr.deviceservice.service;

import com.sayfr.deviceservice.data_access.DeviceDao;
import com.sayfr.deviceservice.data_access.MedicalStaffMemberDao;
import com.sayfr.deviceservice.data_access.PatientDao;
import com.sayfr.deviceservice.data_access.RecordDao;
import com.sayfr.deviceservice.data_access.SensorDao;
import com.sayfr.deviceservice.data_access.SensorReadingRepository;
import com.sayfr.deviceservice.domain.*;
import com.sayfr.deviceservice.domain.Record;
import com.sayfr.deviceservice.services.DataReceiver;
import com.sayfr.deviceservice.services.DataRouter;
import com.sayfr.deviceservice.services.DevicesManger;
import com.sayfr.deviceservice.services.Recorder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RecorderTest {
    
    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private MedicalStaffMemberDao medicalStaffMemberDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private SensorDao sensorDao;

    @Autowired 
    private Recorder recorder;

    @Autowired DataRouter router;

    @Autowired 
    SensorReadingRepository sensorReadingRepository;

    @Autowired 
    DevicesManger devicesManger;

    @Autowired
    DataReceiver dataReceiver;

    private Sensor sensor1;
    private Sensor sensor2;
    private MedicalStaffMember medicalStaffMember1;
    private Patient patient1;
    private Device device1;

    @BeforeEach
    public void setUp() {
        sensor1 = new Sensor("ecg", new SensorId(), "Volt", ValueType.FLOAT);
        sensor2 = new Sensor("blood pressure", new SensorId(), "P", ValueType.FLOAT);
        sensorDao.saveSensor(sensor1);
        sensorDao.saveSensor(sensor2);
        DeviceConfiguration configuration = new DeviceConfiguration(3000);
        Map<SensorId,String> mapping = new HashMap<>();
        mapping.put(sensor1.getSensorId(),sensor1.getName());
        mapping.put(sensor2.getSensorId(),sensor2.getName());
        Mapper newMapper = new JsonMapper(mapping, "time");
        device1 = devicesManger.createDevice("Apple Watch", List.of(sensor1.getSensorId(),sensor2.getSensorId()), configuration, newMapper);
        deviceDao.saveDevice(device1);
        medicalStaffMember1 = new MedicalStaffMember(new MedicalStaffMemberId());
        medicalStaffMemberDao.saveMedicalStaffMember(medicalStaffMember1);
        patient1 = new Patient(new PatientId());
        patientDao.savePatient(patient1);
        recorder.startRecording(device1, patient1.getPatientId(), medicalStaffMember1.getMedicalStaffMemberId());
        dataReceiver.addDevice(device1.getDeviceId(), device1.getMapper());
        
        
        /*List<Record> records = recordDao.getAllRecords();
        Instant now = Instant.now();n
        Duration duration = Duration.ofMinutes(2); // Change this to the desired duration
        Instant pastDate = now.minus(duration);*/
        //System.out.println(recordDao.getRecordSegment(pastDate, now, records.get(0).getRecordId()).getData().getData());

        try{
            Thread.sleep(60000);
        }catch(Exception e){
            System.out.println("Didn't wait");  
        }
        recorder.stopRecording(device1);
    }


    @Test
    public void insertTest(){
        
    }
}

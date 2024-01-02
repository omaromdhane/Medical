package com.sayfr.deviceservice.data_access;

import com.sayfr.deviceservice.domain.*;
import com.sayfr.deviceservice.domain.Record;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecordDaoMongoDbImplTest {

    @Autowired
    private RecordDaoMongoDbImpl recordDao;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordDao sensorReadingRepository;

    @BeforeEach
    public void setUp() {
        // Create a test record
        UUID uniqueRecordId = UUID.randomUUID();
        UUID uniquePatientId = UUID.randomUUID();
        UUID uniqueSensorId = UUID.randomUUID();
        UUID uniqueStaffId = UUID.randomUUID();

        RecordId recordId = new RecordId(uniqueRecordId);
        PatientId patientId = new PatientId(uniquePatientId);
        SensorId sensorId = new SensorId(uniqueSensorId);
        MedicalStaffMemberId staffId = new MedicalStaffMemberId(uniqueStaffId);

        Record testRecord = new Record(recordId, patientId, sensorId, staffId, new Date());
        recordDao.saveRecord(testRecord);
    }

    @AfterEach
    public void tearDown() {
        // Clean up database records after each test
        recordRepository.deleteAll();
    }

    @Test
    public void testSaveRecord() {
        // Create a new record with a unique ID
        UUID uniqueRecordId = UUID.randomUUID();
        UUID uniquePatientId = UUID.randomUUID();
        UUID uniqueSensorId = UUID.randomUUID();
        UUID uniqueStaffId = UUID.randomUUID();

        RecordId recordId = new RecordId(uniqueRecordId);
        PatientId patientId = new PatientId(uniquePatientId);
        SensorId sensorId = new SensorId(uniqueSensorId);
        MedicalStaffMemberId staffId = new MedicalStaffMemberId(uniqueStaffId);

        Record newRecord = new Record(recordId, patientId, sensorId, staffId, new Date());

        // Save the new record
        recordDao.saveRecord(newRecord);

        // Retrieve the new record and ensure it exists
        Record retrievedRecord = recordDao.getRecordById(recordId);
        assertNotNull(retrievedRecord);
        assertEquals(recordId, retrievedRecord.getRecordId());
    }

    @Test
    public void testGetRecordById() {
        // Retrieve the test record by its ID
        Record retrievedRecord = recordDao.getRecordById(recordRepository.findAll().get(0).getRecordId());

        // Ensure the retrieved record is not null and has the same ID as the test
        // record
        assertNotNull(retrievedRecord);
        assertEquals(recordRepository.findAll().get(0).getRecordId(), retrievedRecord.getRecordId());
    }

    @Test
    public void testDeleteRecordById() {
        // Delete the test record from the database
        Record testRecord = recordRepository.findAll().get(0);
        RecordId recordId = testRecord.getRecordId();
        recordDao.deleteRecordById(recordId);

        // Try to retrieve the deleted record and ensure it's null
        Record deletedRecord = recordDao.getRecordById(recordId);
        assertNull(deletedRecord);
    }

    /*@Test
    public void testSaveReading() {
        // Create a new record and reading with unique IDs
        UUID uniqueRecordId = UUID.randomUUID();
        UUID uniqueSensorReadingId = UUID.randomUUID();

        RecordId recordId = new RecordId(uniqueRecordId);
        SensorReadingValue<Integer> readingData = new SensorReadingValue<Interger>();
        SensorReadingValue readingData2 = new NumberBasedSensorReadingDataType(43);

        Instant instant = Instant.now();
        SensorReading newReading = new SensorReading(readingData, instant);
        SensorReading newReading2 = new SensorReading(readingData, instant);
        System.out.println(newReading);
        System.out.println(newReading2);

        // Save the reading for the record
        recordDao.saveReading(recordId, newReading);
        recordDao.saveReading(recordId, newReading2);

    }
*/
    @Test
    public void testExistsById() {
        // Create a new record with a unique ID
        UUID uniqueRecordId = UUID.randomUUID();
        UUID uniquePatientId = UUID.randomUUID();
        UUID uniqueSensorId = UUID.randomUUID();
        UUID uniqueStaffId = UUID.randomUUID();

        RecordId recordId = new RecordId(uniqueRecordId);
        PatientId patientId = new PatientId(uniquePatientId);
        SensorId sensorId = new SensorId(uniqueSensorId);
        MedicalStaffMemberId staffId = new MedicalStaffMemberId(uniqueStaffId);

        Record newRecord = new Record(recordId, patientId, sensorId, staffId, new Date());
        Record newRecord1 = new Record(recordId, patientId, sensorId, staffId, new Date());

        // Save the new record
        recordDao.saveRecord(newRecord);
        recordDao.saveRecord(newRecord1);

        // Check if the record exists by its ID
        assertTrue(recordDao.existsById(recordId));

        // Check if a non-existent ID returns false
        assertFalse(recordDao.existsById(new RecordId()));
    }
}

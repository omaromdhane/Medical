package com.sayfr.deviceservice.data_access;

import com.sayfr.deviceservice.domain.MedicalStaffMemberId;
import com.sayfr.deviceservice.domain.MedicalStaffMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MedicalStaffMemberDaoTest {

    @Autowired
    private MedicalStaffMemberDao medicalStaffMemberDao;

    private MedicalStaffMember testMember;

    @BeforeEach
    public void setUp() {
        // Create a test member with a unique ID
        UUID uniqueId = UUID.randomUUID();
        MedicalStaffMemberId memberId = new MedicalStaffMemberId(uniqueId);
        testMember = new MedicalStaffMember(memberId);

        // Save the test member to the database before each test
        // medicalStaffMemberDao.saveMedicalStaffMember(testMember);
    }

    @Test
    public void testSaveMedicalStaffMember() {
        // Create a new member with a unique ID
        UUID uniqueId = UUID.randomUUID();
        MedicalStaffMemberId memberId = new MedicalStaffMemberId(uniqueId);
        MedicalStaffMember newMember = new MedicalStaffMember(memberId);

        // Save the new member
        medicalStaffMemberDao.saveMedicalStaffMember(newMember);

        // Retrieve the new member and ensure it exists
        MedicalStaffMember retrievedMember = medicalStaffMemberDao.findMedicalStaffMemberById(memberId);
        assertNotNull(retrievedMember);
        assertEquals(memberId, retrievedMember.getMedicalStaffMemberId());
    }

    @Test
    public void testFindMedicalStaffMemberById() {
        // Retrieve the test member by its ID
        MedicalStaffMember retrievedMember = medicalStaffMemberDao.findMedicalStaffMemberById(testMember.getMedicalStaffMemberId());

        // Ensure the retrieved member is not null and has the same ID as the test
        // member
        assertNotNull(retrievedMember);
        assertEquals(testMember.getMedicalStaffMemberId(), retrievedMember.getMedicalStaffMemberId());
    }

    @Test
    public void testFindAllMedicalStaffMembers() {
        // Retrieve all medical staff members
        List<MedicalStaffMember> members = medicalStaffMemberDao.findAllMedicalStaffMembers();

        // Ensure the list is not null and contains at least one member (the test
        // member)
        assertNotNull(members);
        assertTrue(members.size() >= 1);
    }

    @Test
    public void testUpdateMedicalStaffMember() {
        // Change the ID of the test member
        MedicalStaffMemberId newId = new MedicalStaffMemberId();
        // testMember.setId(newId);

        // Update the member in the database
        medicalStaffMemberDao.updateMedicalStaffMember(testMember);

        // Retrieve the updated member and ensure its ID matches the new ID
        MedicalStaffMember updatedMember = medicalStaffMemberDao.findMedicalStaffMemberById(newId);
        assertNotNull(updatedMember);
        assertEquals(newId, updatedMember.getMedicalStaffMemberId());
    }

    @Test
    public void testDeleteMedicalStaffMember() {
        // Delete the test member from the database
        medicalStaffMemberDao.deleteMedicalStaffMember(testMember.getMedicalStaffMemberId());

        // Try to retrieve the deleted member and ensure it's null
        MedicalStaffMember deletedMember = medicalStaffMemberDao.findMedicalStaffMemberById(testMember.getMedicalStaffMemberId());
        assertNull(deletedMember);
    }

    @Test
    public void testExistsById() {
        // Create a MedicalStaffMember and save it
        MedicalStaffMemberId medicalStaffId = new MedicalStaffMemberId();
        MedicalStaffMember member = new MedicalStaffMember(medicalStaffId);

        long startTime = System.currentTimeMillis(); // Record start time

        medicalStaffMemberDao.saveMedicalStaffMember(member);

        long endTime = System.currentTimeMillis(); // Record end time

        // Calculate and print the time taken for the database request
        long executionTime = endTime - startTime;
        System.out.println("Time taken for database request (ms): " + executionTime);

        // Check if the MedicalStaffMember exists by its ID
        assertTrue(medicalStaffMemberDao.existsById(medicalStaffId));

        // Check if a non-existent ID returns false
        assertFalse(medicalStaffMemberDao.existsById(new MedicalStaffMemberId()));
    }

    @Test
    public void testPerformanceReadWrite() throws Exception {
        int numThreads = 10; // Number of concurrent threads
        int numOperations = 1000000; // Number of read and write operations per thread

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> {
                try {
                    for (int j = 0; j < numOperations; j++) {
                        long startTime = System.nanoTime();

                        // Simulate write operation
                        UUID uniqueId = UUID.randomUUID();
                        MedicalStaffMemberId medicalStaffId = new MedicalStaffMemberId(uniqueId);
                        MedicalStaffMember member = new MedicalStaffMember(medicalStaffId);
                        medicalStaffMemberDao.saveMedicalStaffMember(member);

                        long writeDuration = System.nanoTime() - startTime;

                        // Simulate read operation
                        startTime = System.nanoTime();
                        MedicalStaffMember retrievedMember = medicalStaffMemberDao
                                .findMedicalStaffMemberById(medicalStaffId);
                        long readDuration = System.nanoTime() - startTime;

                        // Log metrics
                        System.out.println("Write Duration (ns): " + writeDuration);
                        System.out.println("Read Duration (ns): " + readDuration);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Wait for all threads to complete
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

}

package com.sayfr.deviceservice.data_access;

/*import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.services.DevicesManger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceDaoTest {
    @Autowired
    private DeviceDao deviceDao;

    private Device testDevice;

    @Autowired
    DevicesManger devicesManger;

    @BeforeEach
    public void setUp() {
        // Create a test device with a unique ID
        UUID uniqueId = UUID.randomUUID();
        DeviceId deviceId = new DeviceId(uniqueId);
        testDevice = devicesManger.createDevice(null, null, null);

        // Save the test device to the database before each test
        deviceDao.saveDevice(testDevice);
    }

    @Test
    public void testSaveDevice() {
        // Create a new device with a unique ID
        UUID uniqueId = UUID.randomUUID();
        DeviceId deviceId = new DeviceId(uniqueId);
        Device newDevice = new Device(deviceId, "New Device");

        // Save the new device
        deviceDao.saveDevice(newDevice);

        // Retrieve the new device and ensure it exists
        Device retrievedDevice = deviceDao.getDeviceById(deviceId);
        assertNotNull(retrievedDevice);
        assertEquals(deviceId, retrievedDevice.getId());
    }

    @Test
    public void testGetDeviceById() {
        // Retrieve the test device by its ID
        Device retrievedDevice = deviceDao.getDeviceById(testDevice.getId());

        // Ensure the retrieved device is not null and has the same ID as the test device
        assertNotNull(retrievedDevice);
        assertEquals(testDevice.getId(), retrievedDevice.getId());
    }

    @Test
    public void testGetAllDevices() {
        // Retrieve all devices
        List<Device> devices = deviceDao.getAllDevices();

        // Ensure the list is not null and contains at least one device (the test device)
        assertNotNull(devices);
        assertTrue(devices.size() >= 1);
    }

    

    @Test
    public void testDeleteDevice() {
        // Delete the test device from the database
        deviceDao.deleteDevice(testDevice.getId());

        // Try to retrieve the deleted device and ensure it's null
        Device deletedDevice = deviceDao.getDeviceById(testDevice.getId());
        assertNull(deletedDevice);
    }

    @Test
    public void testExistsById() {
        // Check if the test device exists by its ID
        assertTrue(deviceDao.existsById(testDevice.getId()));

        // Check if a non-existent ID returns false
        assertFalse(deviceDao.existsById(new DeviceId()));
    }
}
*/
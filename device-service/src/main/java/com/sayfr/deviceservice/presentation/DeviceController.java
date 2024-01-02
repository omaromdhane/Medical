package com.sayfr.deviceservice.presentation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.services.DevicesManger;
import com.sayfr.deviceservice.services.Recorder;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final Recorder recorder;
    private final DevicesManger devicesManger;


    public DeviceController(Recorder recorder, DevicesManger devicesManger) {
        this.recorder = recorder;
        this.devicesManger = devicesManger;
    }
    

    /*@PostMapping("/{deviceId}/start-recording")
    public void startRecording(
            @PathVariable String deviceId,
            @RequestParam String patientId,
            @RequestParam String staffId) {
        Device device = ;

        // Start recording for each sensor in the device
        recorder.startRecording(device, new PatientId(patientId), new MedicalStaffMemberId(staffId));
    }*/

    @CrossOrigin
    @PostMapping("/{deviceId}/stop-recording")
    public void stopRecording(@PathVariable String deviceId) {
        DeviceId deviceId2 = new DeviceId(UUID.fromString(deviceId));
        Device device = devicesManger.getDeviceById(deviceId2);
        recorder.stopRecording(device);
    }
}

package com.sayfr.deviceservice.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.domain.Mapper;
import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.SensorReading;
import com.sayfr.deviceservice.domain.SensorReadingBatch;

public class DataReceiverImpl implements DataReceiver{
    
    private DataRouter dataRouter;

    private Map<DeviceId, Mapper> mappers;

    public DataReceiverImpl(DataRouter dataRouter) {
        this.dataRouter = dataRouter;
        mappers = new HashMap<>();
    }

    @Override
    public void receiveData(DeviceId deviceId, Object data) {
        Mapper mapper = mappers.get(deviceId);
        if(mapper == null){
            return;
        }
        Map<SensorId, SensorReading> readings = mapper.getReadings(data);
        for (Map.Entry<SensorId, SensorReading> keyPair : readings.entrySet()) {
            dataRouter.publishReading(keyPair.getKey(), new SensorReadingBatch(List.of(keyPair.getValue())));
        }

    }

    public void addDevice(DeviceId deviceId, Mapper mapper){
        mappers.putIfAbsent(deviceId, mapper);
    };

}

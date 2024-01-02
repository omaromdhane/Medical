package com.sayfr.deviceservice.services;

import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.SensorReadingBatch;

public interface ReadingSubscriber {
    public void receiveData(SensorId deviceId, SensorReadingBatch data);
}

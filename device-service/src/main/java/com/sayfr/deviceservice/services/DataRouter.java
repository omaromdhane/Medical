package com.sayfr.deviceservice.services;

import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.SensorReadingBatch;

public interface DataRouter {
    public void subscribeOnAllReadings(ReadingSubscriber listener);
    public void subscribeOnReadings(ReadingSubscriber listener, SensorId sensorId);
    public void unsubscribeOnAllReadings(ReadingSubscriber listener);
    public void unsubscribeOnReadings(ReadingSubscriber subscriber, SensorId sensorId);
    public void publishReading(SensorId sensorId, SensorReadingBatch data);
}

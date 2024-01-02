package com.sayfr.deviceservice.services;

import com.sayfr.deviceservice.domain.SensorId;
import com.sayfr.deviceservice.domain.SensorReadingBatch;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataRouterImpl implements DataRouter{

    private final Set<ReadingSubscriber> allReadingsSubscribers;
    private final Map<SensorId, Set<ReadingSubscriber>> subscribers;

    public DataRouterImpl() {
        this.allReadingsSubscribers = new HashSet<>();
        this.subscribers = new ConcurrentHashMap<>();
    }

    public void subscribeOnAllReadings(ReadingSubscriber subscriber) {
        allReadingsSubscribers.add(subscriber);
        for (Map.Entry<SensorId, Set<ReadingSubscriber>> element : subscribers.entrySet()) {
            element.getValue().remove(subscriber);
        }
    }

    @Override
    public synchronized void subscribeOnReadings(ReadingSubscriber subscriber, SensorId sensorId) {
        if(!allReadingsSubscribers.contains(subscriber)){
            subscribers.putIfAbsent(sensorId,new HashSet<>());
            subscribers.get(sensorId).add(subscriber);
        }
    }

    public void unsubscribeOnAllReadings(ReadingSubscriber subscriber) {
        allReadingsSubscribers.remove(subscriber);
        for (Map.Entry<SensorId, Set<ReadingSubscriber>> element : subscribers.entrySet()) {
            element.getValue().remove(subscriber);
        }
    }

    public void unsubscribeOnReadings(ReadingSubscriber subscriber, SensorId sensorId) {
        subscribers.get(sensorId).remove(subscriber);
    }

    public void publishReading(SensorId sensorId, SensorReadingBatch data) {
        for (ReadingSubscriber subscriber : allReadingsSubscribers) {
            subscriber.receiveData(sensorId, data);
        }
        if(subscribers.containsKey(sensorId)){
            for (ReadingSubscriber readingSubscriber : subscribers.get(sensorId)) {
                readingSubscriber.receiveData(sensorId, data);
            }
        }
    }

}

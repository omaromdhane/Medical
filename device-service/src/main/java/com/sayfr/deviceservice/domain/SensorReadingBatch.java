package com.sayfr.deviceservice.domain;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import com.sayfr.deviceservice.services.SensorReadingTimestampComparator;
import java.util.ArrayList;

public final class SensorReadingBatch {
    
    private final SortedSet<SensorReading> data;
    
    public SensorReadingBatch() {
        this.data = new TreeSet<>(new SensorReadingTimestampComparator());
    }

    public SensorReadingBatch(List<SensorReading> data) {
        this.data = new TreeSet<>(new SensorReadingTimestampComparator());
        for (SensorReading sensorReading : data) {
            addSensorReading(sensorReading);
        }
    }

    public List<SensorReading> getData() {
        List<SensorReading> response = new ArrayList<>(data);
        return Collections.unmodifiableList(response);
    }

    public SortedSet<SensorReading> getDataAsSortedSet() {
        return Collections.unmodifiableSortedSet(data);
    }

    public void addSensorReading(SensorReading sensorReading) {
        data.add(sensorReading);
    }

    public void removeSensorReading(SensorReading sensorReading) {
        data.remove(sensorReading);
    }

    public static SensorReadingBatch combine(SensorReadingBatch batch1, SensorReadingBatch batch2) {
        List<SensorReading> combinedData = new ArrayList<>(batch1.data);
        combinedData.addAll(batch2.data);
        return new SensorReadingBatch(combinedData);
    }

    public boolean isEmpty(){
        return (data == null) || (data.isEmpty());
    }

    @Override
    public String toString() {
        return "{" +
            " data='" + getData() + "'" +
            "}";
    }

 
}

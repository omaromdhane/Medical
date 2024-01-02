package com.sayfr.deviceservice.services;

import java.util.Comparator;

import com.sayfr.deviceservice.domain.SensorReading;

public class SensorReadingTimestampComparator implements Comparator<SensorReading> {

    @Override
    public int compare(SensorReading reading1, SensorReading reading2) {
        return reading1.getTimestamp().compareTo(reading2.getTimestamp());
    }

}


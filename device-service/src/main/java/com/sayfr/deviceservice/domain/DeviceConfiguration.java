package com.sayfr.deviceservice.domain;

import lombok.Getter;

@Getter
public class DeviceConfiguration {

    private int inactivityTimeout;

    public DeviceConfiguration(int inactivityTimeout) {
        this.inactivityTimeout = inactivityTimeout;
    }

}

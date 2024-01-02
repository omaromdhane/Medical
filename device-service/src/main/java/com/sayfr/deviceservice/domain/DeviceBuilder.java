package com.sayfr.deviceservice.domain;

import java.util.Map;

public class DeviceBuilder {

    private int inactivityTimeoutInMilliseconds;
    private Map<SensorId, String> sensorsMapping;
    private String batteryLevelMapping;
    private String timestampMapping;

    private DeviceBuilder() {
    }

    public static class Builder {
        private int inactivityTimeoutInMilliseconds = 3000;
        private Map<SensorId, String> sensorsMapping;
        private String batteryLevelMapping = "battery";
        private String timestampMapping = "timestamp";

        public Builder inactivityTimeoutInMilliseconds(int inactivityTimeoutInMilliseconds) {
            this.inactivityTimeoutInMilliseconds = inactivityTimeoutInMilliseconds;
            return this;
        }

        public Builder addSensor(SensorId sensorId, String mapping) {
            sensorsMapping.putIfAbsent(sensorId,mapping);
            return this;
        }

        public Builder batteryLevelMapping(String batteryLevelMapping) {
            this.batteryLevelMapping = batteryLevelMapping;
            return this;
        }

        public Builder timestampMapping(String timestampMapping) {
            this.timestampMapping = timestampMapping;
            return this;
        }

        public DeviceBuilder build() {
            DeviceBuilder config = new DeviceBuilder();
            config.inactivityTimeoutInMilliseconds = this.inactivityTimeoutInMilliseconds;
            config.sensorsMapping = this.sensorsMapping;
            config.batteryLevelMapping = this.batteryLevelMapping;
            config.timestampMapping = this.timestampMapping;
            return config;
        }
    }

    public int getInactivityTimeoutInMilliseconds() {
        return inactivityTimeoutInMilliseconds;
    }

    public Map<SensorId, String> getSensorsMapping() {
        return sensorsMapping;
    }

    public String getBatteryLevelMapping() {
        return batteryLevelMapping;
    }

    public String getTimestampMapping() {
        return timestampMapping;
    }
}


package com.sayfr.deviceservice.domain;

/**
 * Represents a communication protocol used for device communication.
 */
public class Protocol {

    private String protocolName;
    
    private String protocolVersion;

    /**
     * Creates a new instance of the protocol.
     *
     * @param protocolName    The name of the protocol (e.g., HTTP, MQTT, Bluetooth).
     * @param protocolVersion The version of the protocol.
     */
    public Protocol(String protocolName, String protocolVersion) {
        this.protocolName = protocolName;
        this.protocolVersion = protocolVersion;
    }

    /**
     * Get the name of the protocol.
     *
     * @return The name of the protocol.
     */
    public String getProtocolName() {
        return protocolName;
    }

    /**
     * Get the version of the protocol.
     *
     * @return The version of the protocol.
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Initialize a connection using the protocol.
     */
    public void initializeConnection() {
        System.out.println("Initializing connection using " + protocolName + " v" + protocolVersion);
    }

}

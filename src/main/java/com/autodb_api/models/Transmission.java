package com.autodb_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "transmission")
public class Transmission {

    private String transmission;

    @Field("transmission_display")
    private String transmissionDisplay;

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getTransmissionDisplay() {
        return transmissionDisplay;
    }

    public void setTransmissionDisplay(String transmissionDisplay) {
        this.transmissionDisplay = transmissionDisplay;
    }

}
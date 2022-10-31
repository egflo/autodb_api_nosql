package com.autodb_api.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "drivetrain")
public class Drivetrain {
    @Field("wheel_system")
    private String wheelSystem;

    @Field("wheel_system_display")
    private String wheelSystemDisplay;

    public String getWheelSystem() {
        return wheelSystem;
    }

    public void setWheelSystem(String wheelSystem) {
        this.wheelSystem = wheelSystem;
    }

    public String getWheelSystemDisplay() {
        return wheelSystemDisplay;
    }

    public void setWheelSystemDisplay(String wheelSystemDisplay) {
        this.wheelSystemDisplay = wheelSystemDisplay;
    }
}

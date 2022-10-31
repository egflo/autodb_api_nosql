package com.autodb_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "body")
public class Body {
    /**
     *                     "body_type": body_type,
     *                     "bed": bed,
     *                     "bed_height": bed_height,
     *                     "bed_length": bed_length,
     *                     "cabin_length": cabin_length,
     *                     "back_legroom": back_legroom,
     *                     "front_legroom": front_legroom,
     *                     "height": height,
     *                     "length": length,
     *                     "wheelbase": wheelbase,
     *                     "width": width,
     */
    private String bodyType;
    private String bed;
    private String bedHeight;
    private String bedLength;
    private String cabinLength;
    private String backLegroom;
    private String frontLegroom;
    private String height;
    private String length;
    private String wheelbase;
    private String width;

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getBedHeight() {
        return bedHeight;
    }

    public void setBedHeight(String bedHeight) {
        this.bedHeight = bedHeight;
    }

    public String getBedLength() {
        return bedLength;
    }

    public void setBedLength(String bedLength) {
        this.bedLength = bedLength;
    }

    public String getCabinLength() {
        return cabinLength;
    }

    public void setCabinLength(String cabinLength) {
        this.cabinLength = cabinLength;
    }

    public String getBackLegroom() {
        return backLegroom;
    }

    public void setBackLegroom(String backLegroom) {
        this.backLegroom = backLegroom;
    }

    public String getFrontLegroom() {
        return frontLegroom;
    }

    public void setFrontLegroom(String frontLegroom) {
        this.frontLegroom = frontLegroom;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(String wheelbase) {
        this.wheelbase = wheelbase;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}

package com.autodb_api.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "report")
public class Report {
    @Field("frame_damaged")
    private String frameDamage;

    @Field("has_accidents")
    private String hasAccidents;

    @Field("isCabriolet")
    private String isCabriolet;

    @Field("is_certified")
    private String isCertified;

    @Field("is_cpo")
    private String isCpo;

    @Field("is_new")
    private String isNew;

    @Field("is_oemcpo")
    private String isOemcpo;

    @Field("owner_count")
    private String ownerCount;

    @Field("salvage")
    private String salvage;

    @Field("theft_title")
    private String theftTitle;

    @Field("vehicle_damage_category")
    private String vehicleDamageCategory;


    public String getFrameDamage() {
        return frameDamage;
    }

    public void setFrameDamage(String frameDamage) {
        this.frameDamage = frameDamage;
    }

    public String getHasAccidents() {
        return hasAccidents;
    }

    public void setHasAccidents(String hasAccidents) {
        this.hasAccidents = hasAccidents;
    }

    public String getIsCabriolet() {
        return isCabriolet;
    }

    public void setIsCabriolet(String isCabriolet) {
        this.isCabriolet = isCabriolet;
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }

    public String getIsCpo() {
        return isCpo;
    }

    public void setIsCpo(String isCpo) {
        this.isCpo = isCpo;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getIsOemcpo() {
        return isOemcpo;
    }

    public void setIsOemcpo(String isOemcpo) {
        this.isOemcpo = isOemcpo;
    }


    public String getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(String ownerCount) {
        this.ownerCount = ownerCount;
    }

    public String getSalvage() {
        return salvage;
    }

    public void setSalvage(String salvage) {
        this.salvage = salvage;
    }

    public String getTheftTitle() {
        return theftTitle;
    }

    public void setTheftTitle(String theftTitle) {
        this.theftTitle = theftTitle;
    }

    public String getVehicleDamageCategory() {
        return vehicleDamageCategory;
    }

    public void setVehicleDamageCategory(String vehicleDamageCategory) {
        this.vehicleDamageCategory = vehicleDamageCategory;
    }


}

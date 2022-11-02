package com.autodb_api.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "report")
public class Report {
    @Field("frame_damaged")
    private Boolean frameDamage;

    @Field("has_accidents")
    private Boolean hasAccidents;

    @Field("isCabriolet")
    private Boolean isCabriolet;

    @Field("is_certified")
    private Boolean isCertified;

    @Field("is_cpo")
    private Boolean isCpo;

    @Field("is_new")
    private Boolean isNew;

    @Field("is_oemcpo")
    private Boolean isOemcpo;

    @Field("owner_count")
    private String ownerCount;

    @Field("salvage")
    private Boolean salvage;

    @Field("theft_title")
    private String theftTitle;

    @Field("vehicle_damage_category")
    private String vehicleDamageCategory;


    public Boolean getFrameDamage() {
        return frameDamage;
    }

    public void setFrameDamage(Boolean frameDamage) {
        this.frameDamage = frameDamage;
    }

    public Boolean getHasAccidents() {
        return hasAccidents;
    }

    public void setHasAccidents(Boolean hasAccidents) {
        this.hasAccidents = hasAccidents;
    }

    public Boolean getIsCabriolet() {
        return isCabriolet;
    }

    public void setIsCabriolet(Boolean isCabriolet) {
        this.isCabriolet = isCabriolet;
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    public Boolean getIsCpo() {
        return isCpo;
    }

    public void setIsCpo(Boolean isCpo) {
        this.isCpo = isCpo;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsOemcpo() {
        return isOemcpo;
    }

    public void setIsOemcpo(Boolean isOemcpo) {
        this.isOemcpo = isOemcpo;
    }


    public String getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(String ownerCount) {
        this.ownerCount = ownerCount;
    }

    public Boolean getSalvage() {
        return salvage;
    }

    public void setSalvage(Boolean salvage) {
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

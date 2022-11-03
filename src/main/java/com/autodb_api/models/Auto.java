package com.autodb_api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "auto")
public class Auto {

    @Id
    private ObjectId id;

    private Body body;

    private Engine engine;

    private Transmission transmission;

    private Drivetrain drivetrain;

    private Report report;

    private Trim trim;

    private String make;

    private String model;

    private String year;

    private String mileage;

    @Field("maximum_seating")
    private String maximumSeating;

    @Field("listing_id")
    private String listingId;

    @Field("listing_color")
    private String listingColor;

    @Field("interior_color")
    private String interiorColor;

    private List<String> images;

    private List<String> options;

    @Field("highway_mpg")
    private String highwayMpg;

    @Field("combine_mpg")
    private String combinedMpg;

    @Field("fuel_type")
    private String fuelType;

    @Field("fuel_tank_capacity")
    private String fuelTankCapacity;

    @Field("exterior_color")
    private String exteriorColor;

    private String description;

    private Double price;

    private String vin;

    private Dealer dealer;

    private String cityMpg;

    @Field("listeddate")
    private Date listedDate;

    @Field("daysonmarket")
    private String daysOnMarket;


    public String getId() {

        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMaximumSeating() {
        return maximumSeating;
    }

    public void setMaximumSeating(String maximumSeating) {
        this.maximumSeating = maximumSeating;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getListingColor() {
        return listingColor;
    }

    public void setListingColor(String listingColor) {
        this.listingColor = listingColor;
    }

    public Date getListedDate() {
        return listedDate;
    }

    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getHighwayMpg() {
        return highwayMpg;
    }

    public void setHighwayMpg(String highwayMpg) {
        this.highwayMpg = highwayMpg;
    }

    public String getCombinedMpg() {
        return combinedMpg;
    }

    public void setCombinedMpg(String combinedMpg) {
        this.combinedMpg = combinedMpg;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(String fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }


    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String getCityMpg() {
        return cityMpg;
    }

    public void setCityMpg(String cityMpg) {
        this.cityMpg = cityMpg;
    }


    public String getDaysOnMarket() {
        return daysOnMarket;
    }

    public void setDaysOnMarket(String daysOnMarket) {
        this.daysOnMarket = daysOnMarket;
    }

}

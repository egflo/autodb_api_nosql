package com.autodb_api.models;


import com.mongodb.client.model.geojson.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dealer")
public class Dealer {
    @Id
    private Integer id;

    private Double longitude;

    private Double latitude;

    private String postcode;

    private String name;

    private Double spId;

    private Double rating;

    private String city;

    private Boolean franchiseDealer;

    private String franchiseMake;

    private Point location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpId() {
        return spId;
    }

    public void setSpId(Double spId) {
        this.spId = spId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getFranchiseDealer() {
        return franchiseDealer;
    }

    public void setFranchiseDealer(Boolean franchiseDealer) {
        this.franchiseDealer = franchiseDealer;
    }

    public String getFranchiseMake() {
        return franchiseMake;
    }

    public void setFranchiseMake(String franchiseMake) {
        this.franchiseMake = franchiseMake;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }


}
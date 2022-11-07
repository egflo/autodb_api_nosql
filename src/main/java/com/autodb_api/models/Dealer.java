package com.autodb_api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "dealer")
public class Dealer {
    private String longitude;

    private String latitude;

    @Field("dealer_zip")
    private String postcode;
    @Field("sp_name")
    private String name;

    @Field("sp_id")
    private String spId;

    private String city;
    @Field("franchise_dealer")
    private Boolean franchiseDealer;

    @Field("franchise_make")
    private String franchiseMake;

    @Field("seller_rating")
    private Double sellerRating;

    @JsonIgnore
    private GeoJsonPoint point;

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

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public Double getRating() {
        return sellerRating;
    }

    public void setRating(Double rating) {
        this.sellerRating = rating;
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

    public Double getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Double sellerRating) {
        this.sellerRating = sellerRating;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
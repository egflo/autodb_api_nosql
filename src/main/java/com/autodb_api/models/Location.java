package com.autodb_api.models;

import com.mongodb.client.model.geojson.Point;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Document(collection = "location")
public class Location {
    @Id
    private ObjectId id;

    private String postcode;

    private GeoJsonPoint point;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public GeoJsonPoint getPoint() {
        return point;
    }

    public void setPoint(GeoJsonPoint point) {
        this.point = point;
    }

/*
  TODO [JPA Buddy] create field to map the 'point' column
   Available actions: Define target Java type | Uncomment as is | Remove column mapping
  @Column(name = "point", columnDefinition = "geography")
  private Object point;
*/
}
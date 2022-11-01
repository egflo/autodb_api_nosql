package com.autodb_api.models;

import org.bson.types.ObjectId;
import org.locationtech.jts.geom.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Document(collection = "location")
public class Location {
    @Id
    private ObjectId id;

    private Integer postcode;

    private Point point;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

/*
  TODO [JPA Buddy] create field to map the 'point' column
   Available actions: Define target Java type | Uncomment as is | Remove column mapping
  @Column(name = "point", columnDefinition = "geography")
  private Object point;
*/
}
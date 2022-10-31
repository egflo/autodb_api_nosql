package com.autodb_api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Meta {
    @Id
    private ObjectId id;

    private String name;

    private String type;

    private Integer count;


    public String getId() {

        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}

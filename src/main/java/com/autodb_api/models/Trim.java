package com.autodb_api.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "trim")
public class Trim {
    private String trimId;

    @Field("trim_name")
    private String trimName;


    public String getTrimId() {
        return trimId;
    }

    public void setTrimId(String trimId) {
        this.trimId = trimId;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }
}

package com.autodb_api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "meta_types")
public class MetaType {
    @Id
    private ObjectId _id;

    private String type;

    public MetaType() {
    }

    public MetaType(String type) {
        this.type = type;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }
}

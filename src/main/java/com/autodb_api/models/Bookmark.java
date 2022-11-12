package com.autodb_api.models;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "bookmarks")
public class Bookmark {
    @Id
    private ObjectId id;

    private ObjectId autoIdId;
    private String userId;
    private Date created;

    public Bookmark() {
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAutoIdId() {
        return autoIdId.toHexString();
    }

    public void setAutoId(String autoId) {
        this.autoIdId = new ObjectId(autoId);
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

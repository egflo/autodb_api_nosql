package com.autodb_api.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "bookmarks")
public class Bookmark {
    @Id
    private Integer id;

    private Integer autoId;
    private String userId;
    private Date created;

    public Bookmark(Integer id, Integer autoId, String userId, Date created) {
        this.id = id;
        this.autoId = autoId;
        this.userId = userId;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
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

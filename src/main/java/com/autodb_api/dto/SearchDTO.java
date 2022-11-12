package com.autodb_api.dto;

import java.sql.Date;

public class SearchDTO {
    int id;
    String description;
    String userId;
    String query;
    Date created;

    public SearchDTO() {
    }

    public SearchDTO(int id, String description, String userId, String query, Date created) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.query = query;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

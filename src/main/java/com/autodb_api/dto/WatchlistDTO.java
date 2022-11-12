package com.autodb_api.dto;

public class WatchlistDTO {
    String id;
    String autoId;
    String userId;

    public WatchlistDTO() {
    }

    public WatchlistDTO(String autoId, String userId, String id) {
        this.id = id;
        this.autoId = autoId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

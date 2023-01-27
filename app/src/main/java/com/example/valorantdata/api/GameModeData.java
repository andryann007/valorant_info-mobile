package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class GameModeData {
    @SerializedName("uuid")
    String id;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("duration")
    String duration;

    @SerializedName("displayIcon")
    String displayIconPath;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDuration() {
        return duration;
    }

    public String getDisplayIconPath() {
        return displayIconPath;
    }

    public GameModeData(String id, String displayName, String duration, String displayIconPath) {
        this.id = id;
        this.displayName = displayName;
        this.duration = duration;
        this.displayIconPath = displayIconPath;
    }
}

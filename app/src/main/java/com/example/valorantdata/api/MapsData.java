package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class MapsData {
    @SerializedName("uuid")
    String id;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("splash")
    String mapsImagePath;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMapsImagePath() {
        return mapsImagePath;
    }

    public MapsData(String id, String displayName, String mapsImagePath) {
        this.id = id;
        this.displayName = displayName;
        this.mapsImagePath = mapsImagePath;
    }
}

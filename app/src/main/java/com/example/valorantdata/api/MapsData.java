package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class MapsData {
    @SerializedName("uuid")
    String id;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("splash")
    String mapsImagePath;

    @SerializedName("listViewIcon")
    String listViewIcon;

    @SerializedName("displayIcon")
    String displayIcon;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMapsImagePath() {
        return mapsImagePath;
    }

    public String getListViewIcon() {
        return listViewIcon;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public MapsData(String id, String displayName, String mapsImagePath, String listViewIcon, String displayIcon) {
        this.id = id;
        this.displayName = displayName;
        this.mapsImagePath = mapsImagePath;
        this.listViewIcon = listViewIcon;
        this.displayIcon = displayIcon;
    }
}

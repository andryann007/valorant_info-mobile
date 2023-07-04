package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class WeaponSkins {
    @SerializedName("uuid")
    String id;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("displayIcon")
    String displayIcon;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public WeaponSkins(String id, String displayName, String displayIcon) {
        this.id = id;
        this.displayName = displayName;
        this.displayIcon = displayIcon;
    }
}

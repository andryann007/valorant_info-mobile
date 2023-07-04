package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeaponData {
    @SerializedName("uuid")
    String id;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("displayIcon")
    String displayIconPath;

    @SerializedName("weaponStats")
    WeaponStats weaponStats;

    @SerializedName("shopData")
    WeaponShopData weaponShopData;

    @SerializedName("skins")
    ArrayList<WeaponSkins> weaponSkins;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayIconPath() {
        return displayIconPath;
    }

    public WeaponStats getWeaponStats() {
        return weaponStats;
    }

    public WeaponShopData getWeaponShopData() {
        return weaponShopData;
    }

    public ArrayList<WeaponSkins> getWeaponSkins() {
        return weaponSkins;
    }

    public WeaponData(String id, String displayName, String displayIconPath, WeaponStats weaponStats, WeaponShopData weaponShopData, ArrayList<WeaponSkins> weaponSkins) {
        this.id = id;
        this.displayName = displayName;
        this.displayIconPath = displayIconPath;
        this.weaponStats = weaponStats;
        this.weaponShopData = weaponShopData;
        this.weaponSkins = weaponSkins;
    }
}

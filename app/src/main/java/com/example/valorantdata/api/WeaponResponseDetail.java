package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class WeaponResponseDetail {
    @SerializedName("data")
    private final WeaponData weaponData;

    public WeaponData getWeaponData() {
        return weaponData;
    }

    public WeaponResponseDetail(WeaponData weaponData) {
        this.weaponData = weaponData;
    }
}

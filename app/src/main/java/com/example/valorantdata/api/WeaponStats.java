package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class WeaponStats {
    @SerializedName("fireRate")
    String weaponFireRate;

    @SerializedName("magazineSize")
    String weaponMagazine;

    @SerializedName("reloadTimeSeconds")
    String reloadTime;

    public String getWeaponFireRate() {
        return weaponFireRate;
    }

    public String getWeaponMagazine() {
        return weaponMagazine;
    }

    public String getReloadTime() {
        return reloadTime;
    }

    public WeaponStats(String weaponFireRate, String weaponMagazine, String reloadTime) {
        this.weaponFireRate = weaponFireRate;
        this.weaponMagazine = weaponMagazine;
        this.reloadTime = reloadTime;
    }
}

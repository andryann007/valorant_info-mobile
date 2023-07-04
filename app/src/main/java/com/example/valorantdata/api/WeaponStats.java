package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class WeaponStats {
    @SerializedName("fireRate")
    double fireRate;

    @SerializedName("runSpeedMultiplier")
    double runSpeed;

    @SerializedName("magazineSize")
    int magazineSize;

    @SerializedName("equipTimeSeconds")
    double equipTime;

    @SerializedName("reloadTimeSeconds")
    double reloadTime;

    @SerializedName("fireMode")
    String fireMode;

    public double getFireRate() {
        return fireRate;
    }

    public double getRunSpeed() {
        return runSpeed;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public double getEquipTime() {
        return equipTime;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public String getFireMode() {
        return fireMode;
    }

    public WeaponStats(double fireRate, double runSpeed, int magazineSize, double equipTime, double reloadTime, String fireMode) {
        this.fireRate = fireRate;
        this.runSpeed = runSpeed;
        this.magazineSize = magazineSize;
        this.equipTime = equipTime;
        this.reloadTime = reloadTime;
        this.fireMode = fireMode;
    }
}

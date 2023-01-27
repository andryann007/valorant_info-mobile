package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class WeaponShopData {
    @SerializedName("cost")
    int cost;

    @SerializedName("category")
    String categoryName;

    public int getCost() {
        return cost;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public WeaponShopData(int cost, String categoryName) {
        this.cost = cost;
        this.categoryName = categoryName;
    }
}

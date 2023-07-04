package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class MapsResponseDetail {
    @SerializedName("data")
    private final MapsData mapsData;

    public MapsData getMapsData() {
        return mapsData;
    }

    public MapsResponseDetail(MapsData mapsData) {
        this.mapsData = mapsData;
    }
}

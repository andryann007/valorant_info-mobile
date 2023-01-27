package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MapsResponse {
    @SerializedName("data")
    private final List<MapsData> data = new ArrayList<>();

    @SerializedName("status")
    int status;

    @SerializedName("error")
    String errorMessage;

    public List<MapsData> getData(){ return data; }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public MapsResponse(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}

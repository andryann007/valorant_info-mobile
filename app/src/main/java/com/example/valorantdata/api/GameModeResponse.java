package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameModeResponse {
    @SerializedName("data")
    private final List<GameModeData> data = new ArrayList<>();

    @SerializedName("status")
    int status;

    @SerializedName("error")
    String errorMessage;

    public List<GameModeData> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public GameModeResponse(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}

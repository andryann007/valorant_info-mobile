package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class AgentAbilities {
    @SerializedName("displayName")
    String abilityName;

    @SerializedName("displayIcon")
    String abilityIconPath;

    public String getAbilityName() {
        return abilityName;
    }

    public String getAbilityIconPath() {
        return abilityIconPath;
    }
}

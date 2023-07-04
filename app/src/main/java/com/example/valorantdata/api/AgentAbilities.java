package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class AgentAbilities {
    @SerializedName("slot")
    String slot;

    @SerializedName("displayName")
    String abilityName;

    @SerializedName("displayIcon")
    String abilityIconPath;

    @SerializedName("description")
    String description;

    public String getSlot() {
        return slot;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getAbilityIconPath() {
        return abilityIconPath;
    }

    public String getDescription() {
        return description;
    }

    public AgentAbilities(String slot, String abilityName, String abilityIconPath, String description) {
        this.slot = slot;
        this.abilityName = abilityName;
        this.abilityIconPath = abilityIconPath;
        this.description = description;
    }
}

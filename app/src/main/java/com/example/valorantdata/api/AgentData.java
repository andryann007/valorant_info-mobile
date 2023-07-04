package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AgentData {
    @SerializedName("uuid")
    String id;

    @SerializedName("background")
    String backgroundPath;

    @SerializedName("displayIcon")
    String displayIconPath;

    @SerializedName("fullPortrait")
    String fullPortrait;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("role")
    AgentRole agentRole;

    @SerializedName("description")
    String description;

    @SerializedName("abilities")
    ArrayList<AgentAbilities> agentAbilities;

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public String getDisplayIconPath() {
        return displayIconPath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public AgentRole getAgentRole() {
        return agentRole;
    }

    public String getFullPortrait() {
        return fullPortrait;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<AgentAbilities> getAgentAbilities() {
        return agentAbilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AgentData(String id, String backgroundPath, String displayIconPath, String fullPortrait, String displayName, AgentRole agentRole, String description, ArrayList<AgentAbilities> agentAbilities) {
        this.id = id;
        this.backgroundPath = backgroundPath;
        this.displayIconPath = displayIconPath;
        this.fullPortrait = fullPortrait;
        this.displayName = displayName;
        this.agentRole = agentRole;
        this.description = description;
        this.agentAbilities = agentAbilities;
    }
}

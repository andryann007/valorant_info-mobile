package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgentData {
    @SerializedName("uuid")
    String id;

    @SerializedName("background")
    String backgroundPath;

    @SerializedName("displayIcon")
    String displayIconPath;

    @SerializedName("displayName")
    String displayName;

    @SerializedName("role")
    AgentRole agentRole;

    @SerializedName("abilities")
    List<AgentAbilities> agentAbilities;

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

    public List<AgentAbilities> getAgentAbilities() {
        return agentAbilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AgentData(String id, String backgroundPath, String displayIconPath, String displayName, AgentRole agentRole, List<AgentAbilities> agentAbilities) {
        this.id = id;
        this.backgroundPath = backgroundPath;
        this.displayIconPath = displayIconPath;
        this.displayName = displayName;
        this.agentRole = agentRole;
        this.agentAbilities = agentAbilities;
    }
}

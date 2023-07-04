package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class AgentRole {
    @SerializedName("displayName")
    String roleName;

    @SerializedName("description")
    String roleDescription;

    @SerializedName("displayIcon")
    String displayIcon;

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public AgentRole(String roleName, String roleDescription, String displayIcon) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.displayIcon = displayIcon;
    }
}

package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class AgentRole {
    @SerializedName("displayName")
    String roleName;

    @SerializedName("description")
    String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public AgentRole(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
}

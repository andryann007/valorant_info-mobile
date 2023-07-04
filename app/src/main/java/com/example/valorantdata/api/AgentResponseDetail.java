package com.example.valorantdata.api;

import com.google.gson.annotations.SerializedName;

public class AgentResponseDetail {
    @SerializedName("data")
    private final AgentData agentData;

    public AgentData getAgentData() {
        return agentData;
    }

    public AgentResponseDetail(AgentData agentData) {
        this.agentData = agentData;
    }
}

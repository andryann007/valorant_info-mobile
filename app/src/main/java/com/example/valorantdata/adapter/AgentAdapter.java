package com.example.valorantdata.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.api.AgentData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.AgentViewHolder>{
    private final List<AgentData> agentData;

    private final Context context;

    public AgentAdapter(List<AgentData> agentData, Context context) {
        this.agentData = agentData;
        this.context = context;
    }

    @NonNull
    @Override
    public AgentAdapter.AgentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AgentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_agent, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AgentAdapter.AgentViewHolder holder, int position) {
        holder.bindItem(agentData.get(position), context);
    }

    @Override
    public int getItemCount() {
        return agentData.size();
    }

    static class AgentViewHolder extends RecyclerView.ViewHolder{
        private final TextView textAgentName, textAgentRole;

        private final ImageView agentImage;

        public AgentViewHolder(@NonNull View itemView) {
            super(itemView);

            agentImage = itemView.findViewById(R.id.imgAgentIcon);
            textAgentName = itemView.findViewById(R.id.tvAgentName);
            textAgentRole = itemView.findViewById(R.id.agentRole);
        }

        public void bindItem(AgentData agentData, Context context) {
            Uri iconUrl = Uri.parse(agentData.getDisplayIconPath());
            Picasso.get().load(iconUrl).into(agentImage);

            textAgentName.setText(agentData.getDisplayName());
            textAgentRole.setText(agentData.getAgentRole().getRoleName());
        }
    }
}

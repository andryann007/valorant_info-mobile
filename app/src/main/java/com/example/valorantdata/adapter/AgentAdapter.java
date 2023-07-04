package com.example.valorantdata.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.activities.DetailActivity;
import com.example.valorantdata.api.AgentData;
import com.makeramen.roundedimageview.RoundedImageView;
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
        private final ProgressBar loadingAgentImage;
        private final TextView textAgentName, textAgentRole;
        private final RoundedImageView agentImage;

        public AgentViewHolder(@NonNull View itemView) {
            super(itemView);

            loadingAgentImage = itemView.findViewById(R.id.loadingAgentImage);
            agentImage = itemView.findViewById(R.id.imgAgentIcon);
            textAgentName = itemView.findViewById(R.id.tvAgentName);
            textAgentRole = itemView.findViewById(R.id.tvAgentRole);
        }

        public void bindItem(AgentData agentData, Context context) {
            if(agentData.getDisplayIconPath() != null ){
                loadingAgentImage.setVisibility(View.GONE);
                Uri iconUrl = Uri.parse(agentData.getDisplayIconPath());
                Picasso.get().load(iconUrl).into(agentImage);
            } else {
                loadingAgentImage.setVisibility(View.GONE);
                agentImage.setImageResource(R.drawable.ic_no_image);
                agentImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }

            setAgentName(textAgentName, agentData.getDisplayName());
            setAgentRole(textAgentRole, agentData.getAgentRole().getRoleName());

            itemView.setOnClickListener(v -> {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("type", "agents");
                i.putExtra("id", agentData.getId());
                context.startActivity(i);
            });
        }
    }
    private static void setAgentName(TextView tv, String textAgentName){
        tv.setText(HtmlCompat.fromHtml("Name : <b>" + textAgentName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setAgentRole(TextView tv, String textAgentRole){
        tv.setText(HtmlCompat.fromHtml("Role : <b>" + textAgentRole + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}

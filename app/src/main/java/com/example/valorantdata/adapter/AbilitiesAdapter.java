package com.example.valorantdata.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.api.AgentAbilities;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.AgentAbilitiesViewHolder> {
    private final ArrayList<AgentAbilities> agentAbilities;

    public AbilitiesAdapter(ArrayList<AgentAbilities> agentAbilities) {
        this.agentAbilities = agentAbilities;
    }

    @NonNull
    @Override
    public AgentAbilitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AgentAbilitiesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_abilities, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AgentAbilitiesViewHolder holder, int position) {
        holder.bindItem(agentAbilities.get(position));
    }

    @Override
    public int getItemCount() {
        return agentAbilities.size();
    }

    static class AgentAbilitiesViewHolder extends RecyclerView.ViewHolder{

        private final RoundedImageView imgAbilitiesIcon;
        private final TextView textAbilitiesSlot, textAbilitiesName, textAbilitiesDescription;
        public AgentAbilitiesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAbilitiesIcon = itemView.findViewById(R.id.imgAbilitiesIcon);
            textAbilitiesSlot = itemView.findViewById(R.id.tvAbilitiesSlot);
            textAbilitiesName = itemView.findViewById(R.id.tvAbilitiesName);
            textAbilitiesDescription = itemView.findViewById(R.id.tvAbilitiesDescription);
        }

        void bindItem(AgentAbilities agentAbilities){
            if(agentAbilities.getAbilityIconPath() != null){
                Uri iconUrl = Uri.parse(agentAbilities.getAbilityIconPath());
                Picasso.get().load(iconUrl).into(imgAbilitiesIcon);
            } else {
                imgAbilitiesIcon.setImageResource(R.drawable.ic_no_image);
                imgAbilitiesIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }

            if(agentAbilities.getAbilityName() != null){
                setAbilitiesName(textAbilitiesName, agentAbilities.getAbilityName());
            } else {
                setAbilitiesName(textAbilitiesName, "-");
            }

            if(agentAbilities.getDescription() != null){
                setAbilitiesDescription(textAbilitiesDescription, agentAbilities.getDescription());
            } else {
                setAbilitiesDescription(textAbilitiesDescription, "-");
            }

            if(agentAbilities.getSlot() != null){
                textAbilitiesSlot.setText(agentAbilities.getSlot());
            } else {
                textAbilitiesSlot.setText("");
            }
        }
    }

    private static void setAbilitiesName(TextView tv, String textAbilitiesName){
        tv.setText(HtmlCompat.fromHtml("Abilities Name : <b>" + textAbilitiesName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setAbilitiesDescription(TextView tv, String textAbilitiesDescription){
        tv.setText(HtmlCompat.fromHtml("Description : <b>" + textAbilitiesDescription + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}

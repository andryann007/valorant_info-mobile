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
import com.example.valorantdata.api.MapsData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MapsViewHolder>{
    private final List<MapsData> mapsData;

    private final Context context;

    public MapsAdapter(List<MapsData> mapsData, Context context) {
        this.mapsData = mapsData;
        this.context = context;
    }

    @NonNull
    @Override
    public MapsAdapter.MapsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MapsAdapter.MapsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_maps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MapsAdapter.MapsViewHolder holder, int position) {
        holder.bindItem(mapsData.get(position), context);
    }

    @Override
    public int getItemCount() {
        return mapsData.size();
    }

    static class MapsViewHolder extends RecyclerView.ViewHolder{
        private final TextView mapsName;
        private final ImageView mapsImage;

        public MapsViewHolder(@NonNull View itemView) {
            super(itemView);
            mapsName = itemView.findViewById(R.id.tvMapsName);
            mapsImage = itemView.findViewById(R.id.imgMapsIcon);
        }

        public void bindItem(MapsData mapsData, Context context) {
            Uri iconUrl = Uri.parse(mapsData.getMapsImagePath());
            Picasso.get().load(iconUrl).into(mapsImage);

            mapsName.setText(mapsData.getDisplayName());
        }
    }
}

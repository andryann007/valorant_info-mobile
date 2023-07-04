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
import com.example.valorantdata.api.MapsData;
import com.makeramen.roundedimageview.RoundedImageView;
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
        private final ProgressBar loadingMapsImage;
        private final TextView mapsName;
        private final RoundedImageView mapsImage;

        public MapsViewHolder(@NonNull View itemView) {
            super(itemView);

            loadingMapsImage = itemView.findViewById(R.id.loadingMapsImage);
            mapsName = itemView.findViewById(R.id.tvMapsName);
            mapsImage = itemView.findViewById(R.id.imgMapsIcon);
        }

        public void bindItem(MapsData mapsData, Context context) {
            if(mapsData.getListViewIcon() != null){
                loadingMapsImage.setVisibility(View.GONE);
                Uri iconUrl = Uri.parse(mapsData.getListViewIcon());
                Picasso.get().load(iconUrl).into(mapsImage);
            } else {
                loadingMapsImage.setVisibility(View.GONE);
                mapsImage.setImageResource(R.drawable.ic_no_image);
                mapsImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }

            setMapsName(mapsName, mapsData.getDisplayName());

            itemView.setOnClickListener(v -> {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("type", "maps");
                i.putExtra("id", mapsData.getId());
                context.startActivity(i);
            });
        }
    }
    private static void setMapsName(TextView tv, String textMapsName){
        tv.setText(HtmlCompat.fromHtml("Maps Name : <b>" + textMapsName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}

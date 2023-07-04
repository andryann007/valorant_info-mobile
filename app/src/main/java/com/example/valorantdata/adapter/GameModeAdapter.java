package com.example.valorantdata.adapter;

import android.content.Context;
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
import com.example.valorantdata.api.GameModeData;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameModeAdapter extends RecyclerView.Adapter<GameModeAdapter.GameModeViewHolder>{
    private final List<GameModeData> gameModeData;

    private final Context context;

    public GameModeAdapter(List<GameModeData> gameModeData, Context context) {
        this.gameModeData = gameModeData;
        this.context = context;
    }

    @NonNull
    @Override
    public GameModeAdapter.GameModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameModeAdapter.GameModeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_mode, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GameModeAdapter.GameModeViewHolder holder, int position) {
        holder.bindItem(gameModeData.get(position), context);
    }

    @Override
    public int getItemCount() {
        return gameModeData.size();
    }

    static class GameModeViewHolder extends RecyclerView.ViewHolder{
        private final ProgressBar loadingModeImage;
        private final TextView textModeName, textModeDuration;
        private final RoundedImageView modeImage;

        public GameModeViewHolder(@NonNull View itemView) {
            super(itemView);

            loadingModeImage = itemView.findViewById(R.id.loadingModeImage);
            textModeName = itemView.findViewById(R.id.tvModeName);
            textModeDuration = itemView.findViewById(R.id.tvModeDuration);
            modeImage = itemView.findViewById(R.id.imgModeIcon);
        }

        public void bindItem(GameModeData gameModeData, Context context) {
            if(gameModeData.getDisplayIconPath() != null && gameModeData.getDisplayName() != null && gameModeData.getDuration() != null){
                if(gameModeData.getDisplayIconPath() != null){
                    loadingModeImage.setVisibility(View.GONE);
                    Uri iconUrl = Uri.parse(gameModeData.getDisplayIconPath());
                    Picasso.get().load(iconUrl).into(modeImage);
                } else {
                    loadingModeImage.setVisibility(View.GONE);
                    modeImage.setImageResource(R.drawable.ic_no_image);
                    modeImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }

                setModeName(textModeName, gameModeData.getDisplayName());
                setModeDuration(textModeDuration, gameModeData.getDuration());
            }
        }
    }
    private static void setModeName(TextView tv, String textModeName){
        tv.setText(HtmlCompat.fromHtml("Mode Name : <b>" + textModeName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
    private static void setModeDuration(TextView tv, String textModeDuration){
        tv.setText(HtmlCompat.fromHtml("Duration : <b>" + textModeDuration + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}

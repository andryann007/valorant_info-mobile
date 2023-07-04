package com.example.valorantdata.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.api.WeaponSkins;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeaponSkinAdapter extends RecyclerView.Adapter<WeaponSkinAdapter.WeaponSkinViewHolder>{
    private final ArrayList<WeaponSkins> weaponSkins;

    public WeaponSkinAdapter(ArrayList<WeaponSkins> weaponSkins) {
        this.weaponSkins = weaponSkins;
    }

    @NonNull
    @Override
    public WeaponSkinAdapter.WeaponSkinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeaponSkinAdapter.WeaponSkinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_weapon_skin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponSkinAdapter.WeaponSkinViewHolder holder, int position) {
        holder.bindItem(weaponSkins.get(position));
    }

    @Override
    public int getItemCount() {
        return weaponSkins.size();
    }

    static class WeaponSkinViewHolder extends RecyclerView.ViewHolder{
        private final RoundedImageView imgWeaponSkinIcon;
        private final TextView textWeaponSkinName;
        public WeaponSkinViewHolder(@NonNull View itemView) {
            super(itemView);

            imgWeaponSkinIcon = itemView.findViewById(R.id.imgWeaponSkinIcon);
            textWeaponSkinName = itemView.findViewById(R.id.textWeaponSkinName);
        }

        public void bindItem(WeaponSkins weaponSkins) {
            if(weaponSkins.getDisplayIcon() != null){
                Uri iconUrl = Uri.parse(weaponSkins.getDisplayIcon());
                Picasso.get().load(iconUrl).into(imgWeaponSkinIcon);
            } else {
                imgWeaponSkinIcon.setImageResource(R.drawable.ic_no_image);
                imgWeaponSkinIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }

            if(weaponSkins.getDisplayName() != null){
                textWeaponSkinName.setText(weaponSkins.getDisplayName());
            } else {
                textWeaponSkinName.setText("-");
            }
        }
    }
}

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
import com.example.valorantdata.api.WeaponData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>{
    private final List<WeaponData> weaponData;

    private final Context context;

    public WeaponAdapter(List<WeaponData> weaponData, Context context) {
        this.weaponData = weaponData;
        this.context = context;
    }


    @NonNull
    @Override
    public WeaponAdapter.WeaponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeaponAdapter.WeaponViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_weapon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponAdapter.WeaponViewHolder holder, int position) {
        holder.bindItem(weaponData.get(position), context);
    }

    @Override
    public int getItemCount() {
        return weaponData.size();
    }

    static class WeaponViewHolder extends RecyclerView.ViewHolder{
        private final TextView weaponName, weaponCategory, fireRate, magazineSize, reloadSpeed;

        private final ImageView weaponIcon;

        public WeaponViewHolder(@NonNull View itemView) {
            super(itemView);
            weaponName = itemView.findViewById(R.id.tvWeaponName);
            weaponCategory = itemView.findViewById(R.id.weaponCategory);
            fireRate = itemView.findViewById(R.id.weaponFireRate);
            magazineSize = itemView.findViewById(R.id.weaponMagazine);
            reloadSpeed = itemView.findViewById(R.id.weaponReloadSpeed);

            weaponIcon = itemView.findViewById(R.id.imgWeaponIcon);
        }

        public void bindItem(WeaponData weaponData, Context context) {
            if(weaponData.getWeaponShopData() != null && weaponData.getWeaponStats() != null){
                Uri iconUrl = Uri.parse(weaponData.getDisplayIconPath());
                Picasso.get().load(iconUrl).into(weaponIcon);

                weaponName.setText(weaponData.getDisplayName());
                weaponCategory.setText(weaponData.getWeaponShopData().getCategoryName());
                fireRate.setText(weaponData.getWeaponStats().getWeaponFireRate());
                magazineSize.setText(weaponData.getWeaponStats().getWeaponMagazine());
                reloadSpeed.setText(weaponData.getWeaponStats().getReloadTime());
            }
        }
    }
}

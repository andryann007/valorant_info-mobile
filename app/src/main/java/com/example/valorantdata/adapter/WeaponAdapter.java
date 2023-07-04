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
import com.example.valorantdata.api.WeaponData;
import com.makeramen.roundedimageview.RoundedImageView;
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
        private final ProgressBar loadingWeaponIcon;
        private final TextView weaponName, weaponCategory, fireRate, magazineSize, reloadSpeed;

        private final RoundedImageView weaponIcon;

        public WeaponViewHolder(@NonNull View itemView) {
            super(itemView);

            loadingWeaponIcon = itemView.findViewById(R.id.loadingWeaponImage);
            weaponName = itemView.findViewById(R.id.tvWeaponName);
            weaponCategory = itemView.findViewById(R.id.weaponCategory);
            fireRate = itemView.findViewById(R.id.weaponFireRate);
            magazineSize = itemView.findViewById(R.id.weaponMagazine);
            reloadSpeed = itemView.findViewById(R.id.weaponReloadSpeed);
            weaponIcon = itemView.findViewById(R.id.imgWeaponIcon);
        }

        public void bindItem(WeaponData weaponData, Context context) {
            if(weaponData.getWeaponShopData() != null && weaponData.getWeaponStats() != null){
                if(weaponData.getDisplayIconPath() != null){
                    loadingWeaponIcon.setVisibility(View.GONE);
                    Uri iconUrl = Uri.parse(weaponData.getDisplayIconPath());
                    Picasso.get().load(iconUrl).into(weaponIcon);
                } else {
                    loadingWeaponIcon.setVisibility(View.GONE);
                    weaponIcon.setImageResource(R.drawable.ic_no_image);
                    weaponIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }

                setWeaponName(weaponName, weaponData.getDisplayName());
                weaponCategory.setText(weaponData.getWeaponShopData().getCategoryName());

                setWeaponFireRate(fireRate, weaponData.getWeaponStats().getFireRate());
                setWeaponMagazine(magazineSize, weaponData.getWeaponStats().getMagazineSize());
                setWeaponReloadSpeed(reloadSpeed, weaponData.getWeaponStats().getReloadTime());

                itemView.setOnClickListener(v -> {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("type", "weapons");
                    i.putExtra("id", weaponData.getId());
                    context.startActivity(i);
                });
            }
        }
    }
    private static void setWeaponName(TextView tv, String textWeaponName){
        tv.setText(HtmlCompat.fromHtml("Weapon Name : <b>" + textWeaponName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponFireRate(TextView tv, double textWeaponFireRate){
        tv.setText(HtmlCompat.fromHtml("Fire Rate : <b>" + textWeaponFireRate + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponMagazine(TextView tv, int textWeaponMagazine){
        tv.setText(HtmlCompat.fromHtml("Magazine : <b>" + textWeaponMagazine + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponReloadSpeed(TextView tv, double textWeaponReloadSpeed){
        tv.setText(HtmlCompat.fromHtml("Reload Speed : <b>" + textWeaponReloadSpeed + " s</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}

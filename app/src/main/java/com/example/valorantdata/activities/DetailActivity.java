package com.example.valorantdata.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import com.example.valorantdata.R;
import com.example.valorantdata.adapter.AbilitiesAdapter;
import com.example.valorantdata.adapter.WeaponSkinAdapter;
import com.example.valorantdata.api.AgentAbilities;
import com.example.valorantdata.api.AgentResponseDetail;
import com.example.valorantdata.api.ApiClient;
import com.example.valorantdata.api.ApiService;
import com.example.valorantdata.api.MapsResponseDetail;
import com.example.valorantdata.api.WeaponResponseDetail;
import com.example.valorantdata.api.WeaponSkins;
import com.example.valorantdata.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {
    private ApiService apiService;
    private AbilitiesAdapter abilitiesAdapter;
    private WeaponSkinAdapter weaponSkinAdapter;
    private final ArrayList<AgentAbilities> agentAbilities = new ArrayList<>();
    private final ArrayList<WeaponSkins> weaponSkins = new ArrayList<>();

    private ActivityDetailBinding binding;
    public static final String language = "en-US";
    private String id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        id = getIntent().getStringExtra("id");
        String type = getIntent().getStringExtra("type");

        if ("agents".equals(type)) {
            binding.detailToolbar.setTitle("Agent Detail Page");
            setSupportActionBar(binding.detailToolbar);

            binding.loadingDetailData.setVisibility(View.GONE);
            binding.agentDetailLayout.setVisibility(View.VISIBLE);
            setAgentDetails();
        } else if ("maps".equals(type)) {
            binding.detailToolbar.setTitle("Maps Detail Page");
            setSupportActionBar(binding.detailToolbar);

            binding.loadingDetailData.setVisibility(View.GONE);
            binding.mapsDetailLayout.setVisibility(View.VISIBLE);
            setMapsDetails();
        } else if ("weapons".equals(type)) {
            binding.detailToolbar.setTitle("Weapon Detail Page");
            setSupportActionBar(binding.detailToolbar);

            binding.loadingDetailData.setVisibility(View.GONE);
            binding.weaponDetailLayout.setVisibility(View.VISIBLE);
            setWeaponDetails();
        }
        binding.detailToolbar.setOnClickListener(v-> onBackPressed());
    }

    private void setAgentDetails() {
        Call<AgentResponseDetail> call = apiService.getAgentDetail(id, language);
        call.enqueue(new Callback<AgentResponseDetail>(){

            @Override
            public void onResponse(@NonNull Call<AgentResponseDetail> call, @NonNull Response<AgentResponseDetail> response) {
                if(response.body() != null){
                    if(response.body().getAgentData().getBackgroundPath() != null){
                        binding.imgAgentBackgroundFullPortrait.setVisibility(View.VISIBLE);
                        Uri backgroundFullPortrait = Uri.parse(response.body().getAgentData().getBackgroundPath());
                        Picasso.get().load(backgroundFullPortrait).into(binding.imgAgentBackgroundFullPortrait);
                    } else {
                        binding.imgAgentBackgroundFullPortrait.setVisibility(View.VISIBLE);
                        binding.imgAgentBackgroundFullPortrait.setImageResource(R.drawable.ic_no_image);
                        binding.imgAgentBackgroundFullPortrait.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getAgentData().getFullPortrait() != null){
                        binding.imgAgentFullPortrait.setVisibility(View.VISIBLE);
                        Uri fullPortrait = Uri.parse(response.body().getAgentData().getFullPortrait());
                        Picasso.get().load(fullPortrait).into(binding.imgAgentFullPortrait);
                    } else {
                        binding.imgAgentFullPortrait.setVisibility(View.VISIBLE);
                        binding.imgAgentFullPortrait.setImageResource(R.drawable.ic_no_image);
                        binding.imgAgentFullPortrait.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getAgentData().getDisplayIconPath() != null){
                        Uri displayIcon = Uri.parse(response.body().getAgentData().getDisplayIconPath());
                        Picasso.get().load(displayIcon).into(binding.imgAgentIcon);
                    } else {
                        binding.imgAgentIcon.setImageResource(R.drawable.ic_no_image);
                        binding.imgAgentIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getAgentData().getAgentRole().getDisplayIcon() != null){
                        Uri roleIcon = Uri.parse(response.body().getAgentData().getAgentRole().getDisplayIcon());
                        Picasso.get().load(roleIcon).into(binding.imgRoleIcon);
                    } else {
                        binding.imgRoleIcon.setImageResource(R.drawable.ic_no_image);
                        binding.imgRoleIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getAgentData().getDisplayName() != null){
                        setAgentName(binding.tvAgentName, response.body().getAgentData().getDisplayName());
                    } else {
                        setAgentName(binding.tvAgentName, "-");
                    }

                    if(response.body().getAgentData().getAgentRole().getRoleName() != null){
                        setAgentRole(binding.tvAgentRole, response.body().getAgentData().getAgentRole().getRoleName());
                        binding.tvRoleName2.setText(response.body().getAgentData().getAgentRole().getRoleName());
                    } else {
                        setAgentRole(binding.tvAgentRole, "-");
                        binding.tvRoleName2.setText("-");
                    }

                    if(response.body().getAgentData().getAgentRole().getRoleDescription() != null){
                        setDescription(binding.tvRoleDescription, response.body().getAgentData().getAgentRole().getRoleDescription());
                    } else {
                        setDescription(binding.tvRoleDescription, "-");
                    }

                    if(response.body().getAgentData().getDescription() != null){
                        binding.textAgentDescription.setText(response.body().getAgentData().getDescription());
                    } else {
                        setNoDescription(binding.textAgentDescription);
                    }

                    setAgentAbilities();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AgentResponseDetail> call, @NonNull Throwable t) {
            }
        });
    }

    private void setAgentAbilities(){
        abilitiesAdapter = new AbilitiesAdapter(agentAbilities);

        Call<AgentResponseDetail> call = apiService.getAgentAbilities(id, language);
        call.enqueue(new Callback<AgentResponseDetail>() {
            @Override
            public void onResponse(@NonNull Call<AgentResponseDetail> call, @NonNull Response<AgentResponseDetail> response) {
                assert response.body() != null;
                if(response.body().getAgentData().getAgentAbilities() != null){
                    binding.rvAgentAbilitiesList.setVisibility(View.VISIBLE);

                    int oldCount = agentAbilities.size();
                    agentAbilities.addAll(response.body().getAgentData().getAgentAbilities());
                    abilitiesAdapter.notifyItemRangeInserted(oldCount, agentAbilities.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AgentResponseDetail> call, @NonNull Throwable t) {

            }
        });
        binding.rvAgentAbilitiesList.setAdapter(abilitiesAdapter);
    }

    private void setMapsDetails(){
        Call<MapsResponseDetail> call = apiService.getMapsDetail(id, language);

        call.enqueue(new Callback<MapsResponseDetail>() {
            @Override
            public void onResponse(@NonNull Call<MapsResponseDetail> call, @NonNull Response<MapsResponseDetail> response) {
                if(response.body() != null){
                    if(response.body().getMapsData().getMapsImagePath() != null){
                        binding.imgMapsSplash.setVisibility(View.VISIBLE);
                        Uri mapsSplash = Uri.parse(response.body().getMapsData().getMapsImagePath());
                        Picasso.get().load(mapsSplash).into(binding.imgMapsSplash);
                    } else {
                        binding.imgMapsSplash.setVisibility(View.VISIBLE);
                        binding.imgMapsSplash.setImageResource(R.drawable.ic_no_image);
                        binding.imgMapsSplash.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getMapsData().getListViewIcon() != null){
                        Uri mapsIcon = Uri.parse(response.body().getMapsData().getListViewIcon());
                        Picasso.get().load(mapsIcon).into(binding.imgMapsIcon);
                    } else {
                        binding.imgMapsIcon.setImageResource(R.drawable.ic_no_image);
                        binding.imgMapsIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getMapsData().getDisplayIcon() != null){
                        Uri mapsLocation = Uri.parse(response.body().getMapsData().getDisplayIcon());
                        Picasso.get().load(mapsLocation).into(binding.imgMapsLocation);
                    } else {
                        binding.imgMapsLocation.setImageResource(R.drawable.ic_no_image);
                        binding.imgMapsLocation.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getMapsData().getDisplayName() != null){
                        setMapsName(binding.tvMapsName, response.body().getMapsData().getDisplayName());
                    } else {
                        setMapsName(binding.tvMapsName, "-");
                    }

                    setMapsLocation(binding.tvMapsLocation);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MapsResponseDetail> call, @NonNull Throwable t) {

            }
        });
    }

    private void setWeaponDetails(){
        Call<WeaponResponseDetail> call = apiService.getWeaponDetail(id, language);
        call.enqueue(new Callback<WeaponResponseDetail>() {
            @Override
            public void onResponse(@NonNull Call<WeaponResponseDetail> call, @NonNull Response<WeaponResponseDetail> response) {
                if(response.body() != null){
                    if(response.body().getWeaponData().getDisplayIconPath() != null){
                        Uri weaponIcon = Uri.parse(response.body().getWeaponData().getDisplayIconPath());
                        Picasso.get().load(weaponIcon).into(binding.imgWeaponIcon);
                    } else {
                        binding.imgWeaponIcon.setImageResource(R.drawable.ic_no_image);
                        binding.imgWeaponIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }

                    if(response.body().getWeaponData().getDisplayName() != null){
                        setWeaponName(binding.textWeaponName, response.body().getWeaponData().getDisplayName());
                    } else {
                        setWeaponName(binding.textWeaponName, "-");
                    }

                    if(response.body().getWeaponData().getWeaponShopData().getCategoryName() != null){
                        setWeaponCategory(binding.tvWeaponCategory, response.body().getWeaponData().getWeaponShopData().getCategoryName());
                    } else {
                        setWeaponCategory(binding.tvWeaponCategory, "-");
                    }

                    if(response.body().getWeaponData().getWeaponShopData().getCost() != 0){
                        setWeaponCost(binding.tvWeaponCost, response.body().getWeaponData().getWeaponShopData().getCost());
                    } else {
                        setWeaponCost(binding.tvWeaponCost, 0);
                    }

                    if(response.body().getWeaponData().getWeaponStats().getFireRate() != 0){
                        setWeaponFireRate(binding.tvWeaponFireRate, response.body().getWeaponData().getWeaponStats().getFireRate());
                    } else {
                        setWeaponFireRate(binding.tvWeaponFireRate, 0);
                    }

                    if(response.body().getWeaponData().getWeaponStats().getRunSpeed() != 0){
                        setWeaponRunSpeed(binding.tvWeaponRunSpeed, response.body().getWeaponData().getWeaponStats().getRunSpeed());
                    } else {
                        setWeaponRunSpeed(binding.tvWeaponRunSpeed, 0);
                    }

                    if(response.body().getWeaponData().getWeaponStats().getMagazineSize() != 0){
                        setWeaponMagazine(binding.tvWeaponMagazine, response.body().getWeaponData().getWeaponStats().getMagazineSize());
                    } else {
                        setWeaponMagazine(binding.tvWeaponMagazine, 0);
                    }

                    if(response.body().getWeaponData().getWeaponStats().getEquipTime() != 0){
                        setWeaponEquipTime(binding.tvWeaponEquipTime, response.body().getWeaponData().getWeaponStats().getEquipTime());
                    } else {
                        setWeaponEquipTime(binding.tvWeaponEquipTime, 0);
                    }

                    if(response.body().getWeaponData().getWeaponStats().getReloadTime() != 0){
                        setWeaponReloadSpeed(binding.tvWeaponReloadTime, response.body().getWeaponData().getWeaponStats().getReloadTime());
                    } else {
                        setWeaponReloadSpeed(binding.tvWeaponReloadTime, 0);
                    }

                    if(response.body().getWeaponData().getWeaponStats().getFireMode() != null){
                        setWeaponFireMode(binding.tvWeaponRunSpeed, response.body().getWeaponData().getWeaponStats().getFireMode());
                    } else {
                        setWeaponFireMode(binding.tvWeaponRunSpeed, "-");
                    }

                    setWeaponSkins();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeaponResponseDetail> call, @NonNull Throwable t) {

            }
        });
    }

    private void setWeaponSkins(){
        weaponSkinAdapter = new WeaponSkinAdapter(weaponSkins);

        Call<WeaponResponseDetail> call = apiService.getWeaponSkins(id, language);
        call.enqueue(new Callback<WeaponResponseDetail>() {
            @Override
            public void onResponse(@NonNull Call<WeaponResponseDetail> call, @NonNull Response<WeaponResponseDetail> response) {
                assert response.body() != null;
                if(response.body().getWeaponData().getWeaponSkins() != null){
                    binding.rvWeaponSkinList.setVisibility(View.VISIBLE);

                    int oldCount = weaponSkins.size();
                    weaponSkins.addAll(response.body().getWeaponData().getWeaponSkins());
                    weaponSkinAdapter.notifyItemRangeInserted(oldCount, weaponSkins.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeaponResponseDetail> call, @NonNull Throwable t) {

            }
        });

        binding.rvWeaponSkinList.setAdapter(weaponSkinAdapter);
    }

    private static void setAgentName(TextView tv, String textAgentName){
        tv.setText(HtmlCompat.fromHtml("Agent Name : <b>" + textAgentName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setAgentRole(TextView tv, String textAgentRole){
        tv.setText(HtmlCompat.fromHtml("Role : <b>" + textAgentRole + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setDescription(TextView tv, String textDescription){
        tv.setText(HtmlCompat.fromHtml("Description : <b>" + textDescription + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setNoDescription(TextView tv){
        tv.setText(HtmlCompat.fromHtml("No Description Yet !!!",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setMapsName(TextView tv, String textMapsName){
        tv.setText(HtmlCompat.fromHtml("Maps Name : <b>" + textMapsName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setMapsLocation(TextView tv){
        tv.setText(HtmlCompat.fromHtml("Maps Location",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponName(TextView tv, String textWeaponName){
        tv.setText(HtmlCompat.fromHtml("Weapon Name : <b>" + textWeaponName + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponCategory(TextView tv, String textWeaponCategory){
        tv.setText(HtmlCompat.fromHtml("Category : <b>" + textWeaponCategory + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponCost(TextView tv, int textWeaponCost){
        tv.setText(HtmlCompat.fromHtml("Cost : <b>" + textWeaponCost + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponFireRate(TextView tv, double textWeaponFireRate){
        tv.setText(HtmlCompat.fromHtml("Fire Rate : <b>" + textWeaponFireRate + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponRunSpeed(TextView tv, double textWeaponRunSpeed){
        tv.setText(HtmlCompat.fromHtml("Run Speed : <b>" + textWeaponRunSpeed + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponMagazine(TextView tv, int textWeaponMagazine){
        tv.setText(HtmlCompat.fromHtml("Magazine : <b>" + textWeaponMagazine + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponEquipTime(TextView tv, double textWeaponEquipTime){
        tv.setText(HtmlCompat.fromHtml("Equip Time : <b>" + textWeaponEquipTime + " s</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponReloadSpeed(TextView tv, double textWeaponReloadSpeed){
        tv.setText(HtmlCompat.fromHtml("Reload Speed : <b>" + textWeaponReloadSpeed + " s</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private static void setWeaponFireMode(TextView tv, String textWeaponFireMode){
        tv.setText(HtmlCompat.fromHtml("Fire Mode : <b>" + textWeaponFireMode + "</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}
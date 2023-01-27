package com.example.valorantdata.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.adapter.WeaponAdapter;
import com.example.valorantdata.api.ApiClient;
import com.example.valorantdata.api.ApiService;
import com.example.valorantdata.api.WeaponData;
import com.example.valorantdata.api.WeaponResponse;
import com.example.valorantdata.databinding.FragmentWeaponBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeaponFragment extends Fragment {

    private ApiService apiService;
    private FragmentWeaponBinding binding;
    private WeaponAdapter weaponAdapter;
    private final List<WeaponData> weaponData = new ArrayList<>();

    private ProgressBar loadingWeaponData;

    public WeaponFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentWeaponBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        setWeaponData(root);

        return root;
    }

    private void setWeaponData(View view) {
        RecyclerView rvWeaponData = view.findViewById(R.id.rvWeaponList);
        weaponAdapter = new WeaponAdapter(weaponData, getContext());
        loadingWeaponData = view.findViewById(R.id.loadingWeaponData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);

        rvWeaponData.setLayoutManager(gridLayoutManager);
        rvWeaponData.setAdapter(weaponAdapter);
        getWeaponData();
    }

    private void getWeaponData() {
        String language = "en-US";
        Call<WeaponResponse> call = apiService.getWeaponList(language);
        call.enqueue(new Callback<WeaponResponse>(){

            @Override
            public void onResponse(@NonNull Call<WeaponResponse> call, @NonNull Response<WeaponResponse> response) {
                if(response.body() != null){
                    loadingWeaponData.setVisibility(View.GONE);
                    int oldCount = weaponData.size();
                    weaponData.addAll(response.body().getData());
                    weaponAdapter.notifyItemChanged(oldCount, weaponData.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeaponResponse> call, @NonNull Throwable t) {
                loadingWeaponData.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Fail to Fetch Data !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
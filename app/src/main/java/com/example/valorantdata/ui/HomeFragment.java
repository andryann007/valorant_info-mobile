package com.example.valorantdata.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.adapter.AgentAdapter;
import com.example.valorantdata.adapter.GameModeAdapter;
import com.example.valorantdata.adapter.MapsAdapter;
import com.example.valorantdata.adapter.WeaponAdapter;
import com.example.valorantdata.api.AgentData;
import com.example.valorantdata.api.AgentResponse;
import com.example.valorantdata.api.ApiClient;
import com.example.valorantdata.api.ApiService;
import com.example.valorantdata.api.GameModeData;
import com.example.valorantdata.api.GameModeResponse;
import com.example.valorantdata.api.MapsData;
import com.example.valorantdata.api.MapsResponse;
import com.example.valorantdata.api.WeaponData;
import com.example.valorantdata.api.WeaponResponse;
import com.example.valorantdata.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    private ApiService apiService;
    private FragmentHomeBinding binding;

    private GameModeAdapter gameModeAdapter;
    private AgentAdapter agentAdapter;
    private WeaponAdapter weaponAdapter;
    private MapsAdapter mapsAdapter;

    private final List<GameModeData> gameModeData = new ArrayList<>();
    private final List<AgentData> agentData = new ArrayList<>();
    private final List<WeaponData> weaponData = new ArrayList<>();
    private final List<MapsData> mapsData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        setGameModeData(root);
        setAgentData(root);
        setWeaponData(root);
        setMapsData(root);

        return root;
    }

    private void setGameModeData(View view) {
        RecyclerView rvGameModeData = view.findViewById(R.id.rvHomeGameMode);
        gameModeAdapter = new GameModeAdapter(gameModeData, getContext());

        rvGameModeData.setAdapter(gameModeAdapter);
        getGameModeData();
    }

    private void getGameModeData() {
        String language = "en-US";
        Call<GameModeResponse> call = apiService.getGameModeList(language);
        call.enqueue(new Callback<GameModeResponse>(){

            @Override
            public void onResponse(@NonNull Call<GameModeResponse> call, @NonNull Response<GameModeResponse> response) {
                if(response.body() != null){
                    int oldCount = gameModeData.size();
                    gameModeData.addAll(response.body().getData());
                    gameModeAdapter.notifyItemChanged(oldCount, gameModeData.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GameModeResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Fail to Fetch Data !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAgentData(View view) {
        RecyclerView rvAgentData = view.findViewById(R.id.rvHomeAgent);
        agentAdapter = new AgentAdapter(agentData, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);

        rvAgentData.setLayoutManager(gridLayoutManager);
        rvAgentData.setAdapter(agentAdapter);
        getAgentData();
    }

    private void getAgentData() {
        String language = "en-US";
        boolean isPlayable = true;
        Call<AgentResponse> call = apiService.getAgentList(language, isPlayable);
        call.enqueue(new Callback<AgentResponse>(){

            @Override
            public void onResponse(@NonNull Call<AgentResponse> call, @NonNull Response<AgentResponse> response) {
                if(response.body() != null){
                    int oldCount = agentData.size();
                    agentData.addAll(response.body().getData());
                    agentAdapter.notifyItemChanged(oldCount, agentData.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AgentResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Fail to Fetch Data !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setWeaponData(View view) {
        RecyclerView rvWeaponData = view.findViewById(R.id.rvHomeWeapon);
        weaponAdapter = new WeaponAdapter(weaponData, getContext());
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
                    int oldCount = weaponData.size();
                    weaponData.addAll(response.body().getData());
                    weaponAdapter.notifyItemChanged(oldCount, weaponData.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeaponResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Fail to Fetch Data !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMapsData(View view) {
        RecyclerView rvMapsData = view.findViewById(R.id.rvHomeMaps);
        mapsAdapter = new MapsAdapter(mapsData, getContext());

        rvMapsData.setAdapter(mapsAdapter);
        getMapsData();
    }

    private void getMapsData() {
        String language = "en-US";
        Call<MapsResponse> call = apiService.getMapsList(language);
        call.enqueue(new Callback<MapsResponse>(){

            @Override
            public void onResponse(@NonNull Call<MapsResponse> call, @NonNull Response<MapsResponse> response) {
                if(response.body() != null){
                    int oldCount = mapsData.size();
                    mapsData.addAll(response.body().getData());
                    mapsAdapter.notifyItemChanged(oldCount, mapsData.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MapsResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Fail to Fetch Data !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
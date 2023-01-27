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
import com.example.valorantdata.adapter.MapsAdapter;
import com.example.valorantdata.api.ApiClient;
import com.example.valorantdata.api.ApiService;
import com.example.valorantdata.api.MapsData;
import com.example.valorantdata.api.MapsResponse;
import com.example.valorantdata.databinding.FragmentMapsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsFragment extends Fragment {

    private ApiService apiService;
    private FragmentMapsBinding binding;
    private MapsAdapter mapsAdapter;
    private final List<MapsData> mapsData = new ArrayList<>();

    private ProgressBar loadingMapsData;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        setMapsData(root);

        return root;
    }

    private void setMapsData(View view) {
        RecyclerView rvMapsData = view.findViewById(R.id.rvMapsList);
        mapsAdapter = new MapsAdapter(mapsData, getContext());
        loadingMapsData = view.findViewById(R.id.loadingMapsData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);

        rvMapsData.setLayoutManager(gridLayoutManager);
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
                    loadingMapsData.setVisibility(View.GONE);
                    int oldCount = mapsData.size();
                    mapsData.addAll(response.body().getData());
                    mapsAdapter.notifyItemChanged(oldCount, mapsData.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MapsResponse> call, @NonNull Throwable t) {
                loadingMapsData.setVisibility(View.GONE);
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
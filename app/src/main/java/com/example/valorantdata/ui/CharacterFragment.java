package com.example.valorantdata.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorantdata.R;
import com.example.valorantdata.adapter.AgentAdapter;
import com.example.valorantdata.api.AgentData;
import com.example.valorantdata.api.AgentResponse;
import com.example.valorantdata.api.ApiClient;
import com.example.valorantdata.api.ApiService;
import com.example.valorantdata.databinding.FragmentCharacterBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CharacterFragment extends Fragment {
    private ApiService apiService;
    private FragmentCharacterBinding binding;
    private AgentAdapter agentAdapter;
    private final List<AgentData> agentData = new ArrayList<>();

    private RecyclerView rvAgentData;
    private ProgressBar loadingAgentData;
    private TextView textNoResult;

    public static final String language = "en-US";
    public static final boolean isPlayable = true;
    public CharacterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Retrofit retrofit = ApiClient.getClient();
        apiService = retrofit.create(ApiService.class);

        setAgentData(root);

        return root;
    }

    private void setAgentData(View view) {
        rvAgentData = view.findViewById(R.id.rvAgentList);
        loadingAgentData = view.findViewById(R.id.loadingAgentData);
        textNoResult = view.findViewById(R.id.textNoAgentResult);

        agentAdapter = new AgentAdapter(agentData, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);

        rvAgentData.setLayoutManager(gridLayoutManager);
        rvAgentData.setAdapter(agentAdapter);
        getAgentData();
    }

    private void getAgentData() {
        Call<AgentResponse> call = apiService.getAgentList(language, isPlayable);
        call.enqueue(new Callback<AgentResponse>(){

            @Override
            public void onResponse(@NonNull Call<AgentResponse> call, @NonNull Response<AgentResponse> response) {
                if(response.body() != null){
                    loadingAgentData.setVisibility(View.GONE);
                    rvAgentData.setVisibility(View.VISIBLE);

                    int oldCount = agentData.size();
                    agentData.addAll(response.body().getData());
                    agentAdapter.notifyItemRangeInserted(oldCount, agentData.size());
                } else {
                    loadingAgentData.setVisibility(View.GONE);
                    textNoResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AgentResponse> call, @NonNull Throwable t) {
                loadingAgentData.setVisibility(View.GONE);
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
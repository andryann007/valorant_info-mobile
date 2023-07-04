package com.example.valorantdata.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("agents")
    Call<AgentResponse> getAgentList(@Query("language") String language,
                                     @Query("isPlayableCharacter") boolean isPlayableCharacter);

    @GET("agents/{agentUuid}")
    Call<AgentResponseDetail> getAgentDetail(@Path("agentUuid") String id, @Query("language") String language);

    @GET("agents/{agentUuid}")
    Call<AgentResponseDetail> getAgentAbilities(@Path("agentUuid") String id, @Query("language") String language);

    @GET("weapons")
    Call<WeaponResponse> getWeaponList(@Query("Language") String language);

    @GET("weapons/{weaponUuid}")
    Call<WeaponResponseDetail> getWeaponDetail(@Path("weaponUuid") String id, @Query("language") String language);

    @GET("weapons/{weaponUuid}")
    Call<WeaponResponseDetail> getWeaponSkins(@Path("weaponUuid") String id, @Query("language") String language);

    @GET("maps")
    Call<MapsResponse> getMapsList(@Query("language") String language);

    @GET("maps/{mapUuid}")
    Call<MapsResponseDetail> getMapsDetail(@Path("mapUuid") String id, @Query("language") String language);

    @GET("gamemodes")
    Call<GameModeResponse> getGameModeList(@Query("language") String language);
}

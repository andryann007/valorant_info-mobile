package com.example.valorantdata.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("agents")
    Call<AgentResponse> getAgentList(@Query("language") String language, @Query("isPlayableCharacter") boolean isPlayableCharacter);

    @GET("agents/{agentUuid}")
    Call<AgentResponse> getAgentDetail(@Path("uuid") String id, @Query("language") String language);

    @GET("weapons")
    Call<WeaponResponse> getWeaponList(@Query("Language") String language);

    @GET("weapons/{weaponUuid}")
    Call<WeaponResponse> getWeaponDetail(@Path("uuid") String id, @Query("language") String language);

    @GET("maps")
    Call<MapsResponse> getMapsList(@Query("language") String language);

    @GET("maps/{mapUuid}")
    Call<MapsResponse> getMapsDetail(@Path("uuid") String id, @Query("language") String language);

    @GET("gamemodes")
    Call<GameModeResponse> getGameModeList(@Query("language") String language);

    @GET("gamemodes/{gamemodeUuid}")
    Call<GameModeResponse> getGameModeDetail(@Path("uuid") String id, @Query("language") String language);
}

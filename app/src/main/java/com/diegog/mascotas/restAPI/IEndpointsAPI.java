package com.diegog.mascotas.restAPI;

import com.diegog.mascotas.restAPI.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by flogog on 6/26/16.
 */
public interface IEndpointsAPI {

    @GET(ConstantsRestAPI.URL_GET_RECENT_MEDIA)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantsRestAPI.URL_GET_USER_MEDIA)
    Call<MascotaResponse> getRecentUserMedia(@Path("user-id") String userId);

    @GET(ConstantsRestAPI.URL_SEARCH_USER)
    Call<MascotaResponse> getUserId(@Query("q") String username);


}

package com.diegog.mascotas.restAPI;

import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.diegog.mascotas.restAPI.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST(ConstantsRestAPI.HEROKU_POST_ID_TOKEN)
    Call<UserResponse> registerHerokuTokenID(@Field("token") String token);

    @FormUrlEncoded
    @POST(ConstantsRestAPI.HEROKU_POST_USER)
    Call<UserResponse> registerHerokuUser(@Field("dispositivo") String dispositivo, @Field("user") String user);

}

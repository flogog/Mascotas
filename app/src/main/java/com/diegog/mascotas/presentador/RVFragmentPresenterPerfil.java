package com.diegog.mascotas.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.diegog.mascotas.R;
import com.diegog.mascotas.fragment.IPerfilRecyclerViewFragment;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flogog on 6/26/16.
 */
public class RVFragmentPresenterPerfil implements IRVFragmentPresenterPerfil {

    private IPerfilRecyclerViewFragment iperfilRVFragment;
    private Context context;
    private ArrayList<Mascota> mascotas;
    public String profilePicURL;



    public RVFragmentPresenterPerfil(IPerfilRecyclerViewFragment iperfilRVFragment, Context context) {
        this.iperfilRVFragment   =   iperfilRVFragment;
        this.context             =   context;

        SharedPreferences preferencias =  this.context.getSharedPreferences("DatosPersonales",this.context.MODE_PRIVATE);
        String usuario = preferencias.getString("Usuario",null);
        System.out.println("++++++++++++++++++++ "+usuario);
        if(usuario!=null && !usuario.equals("")){
            getUserIdByUsername(usuario);
        }
        else{
            getRecentMedia();
        }
        //getRecentMedia();

    }


    @Override
    public void getRecentMedia() {
        RestAdapter restAdapter =  new RestAdapter();
        Gson mediaRecentGson = restAdapter.createGSONDeserializerMediaRecent();
        IEndpointsAPI endpointsAPI = restAdapter.startConnectionRestAPI(mediaRecentGson);

        Call<MascotaResponse> responseCall;
        responseCall = endpointsAPI.getRecentMedia();

        responseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                profilePicURL = mascotaResponse.getProfilePicture();
                showMascotasRV();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable throwable) {
                Toast.makeText(context,"Error en la conextion, Try Again", Toast.LENGTH_LONG);
                Log.i("Error en la conextion",throwable.toString());
            }
        });



    }

    @Override
    public void showMascotasRV() {
        iperfilRVFragment.inicializaAdaptadorRV(iperfilRVFragment.createAdapter(mascotas));
        iperfilRVFragment.generarGridLayout();
        iperfilRVFragment.sendProfilePic(profilePicURL);
    }

    @Override
    public void getMediaByUser(String userId) {
        RestAdapter             restAdapter       = new RestAdapter();
        Gson                    mediaRecentGson   = restAdapter.createGSONDeserializerMediaRecent();
        IEndpointsAPI           endpointsAPI      = restAdapter.startConnectionRestAPI(mediaRecentGson);
        Call<MascotaResponse>   responseCall      = endpointsAPI.getRecentUserMedia(userId);

        responseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                profilePicURL = mascotaResponse.getProfilePicture();
                showMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable throwable) {
                Toast.makeText(context,"Error en la conextion, Try Again", Toast.LENGTH_LONG);
                Log.i("Error en la conextion",throwable.toString());
            }
        });

    }

    @Override
    public void getUserIdByUsername(String username) {
        RestAdapter     restAdapter     =  new RestAdapter();
        Gson            mediaRecentGson = restAdapter.createGSONDeserializerUserId();
        IEndpointsAPI   endpointsAPI    = restAdapter.startConnectionRestAPI(mediaRecentGson);
        Call<MascotaResponse>    responseCall    = endpointsAPI.getUserId(username);

        System.out.println("-------------"+responseCall.toString());
        responseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                System.out.println("+++++++++ "+mascotaResponse.getUserId());
                getMediaByUser(mascotaResponse.getUserId());
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable throwable) {
                Toast.makeText(context,"Error en la conextion, Try Again", Toast.LENGTH_LONG);
                Log.i("Error en la conextion",throwable.toString());
            }
        });
    }


}
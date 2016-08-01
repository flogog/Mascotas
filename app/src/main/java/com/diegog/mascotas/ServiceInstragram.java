package com.diegog.mascotas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.diegog.mascotas.restAPI.model.UserResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flogog on 7/31/16.
 */
public class ServiceInstragram extends BroadcastReceiver {

    public static Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_KEY_F = "FOLLOW_UNFOLLOW";
        String ACTION_KEY_T = "TARGET";
        this.context = context;


        String accion = intent.getAction();

        if (ACTION_KEY_F.equals(accion)){
            Bundle param        = intent.getExtras();
            String userid       = (String) param.get("userid");
            String action       = (String) param.get("action");
            consumeServicioInstagram(context, userid, action);
        }
        else if (ACTION_KEY_T.equals(accion)){
            Bundle param        = intent.getExtras();
            String userid       = (String) param.get("userid");
            getInstragramUsername(userid);
        }

        Intent i = context.getPackageManager()
                .getLaunchIntentForPackage( context.getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(i);

    }

    public void consumeServicioInstagram(Context context, String userId, String action){

        RestAdapter         restAPI          =   new RestAdapter();
        IEndpointsAPI       endpoints        =   restAPI.startInstaRestAPI();
        Call<UserResponse>  userResponseCall =   endpoints.followUserID(userId,action);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String resp = response.body().getResp();
                System.out.println("RESPUESTA FOLLOW "+resp);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Error en la conextion", t.getLocalizedMessage());
                Log.i("Error en la conextion", t.getStackTrace().toString());
            }
        });

        Toast.makeText(context, "Following Instagram User", Toast.LENGTH_SHORT).show();

    }

    public void getInstragramUsername(final String userID){

        RestAdapter             restAdapter       = new RestAdapter();
        Gson                    mediaRecentGson   = restAdapter.createGSONDeserializerMediaRecent();
        IEndpointsAPI           endpointsAPI      = restAdapter.startConnectionRestAPI(mediaRecentGson);
        Call<MascotaResponse>   responseCall      = endpointsAPI.getRecentUserMedia(userID);

        responseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                String username = mascotaResponse.getMascotas().get(0).getUsername();
                guardarPreferencia(username);
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable throwable) {
                //Toast.makeText(context, R.string.connection_error, Toast.LENGTH_LONG).show();
                Log.i("Error ",throwable.toString());
            }
        });
    }

    public void guardarPreferencia(String username){
        SharedPreferences preferencias =  context.getSharedPreferences("DatosPersonales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Usuario",username);

        editor.apply();
    }
}

package com.diegog.mascotas.restAPI.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegog.mascotas.R;
import com.diegog.mascotas.database.ConstructorMascotas;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.restAPI.ConstantsRestAPI;
import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.deserializador.MascotaDeserializador;
import com.diegog.mascotas.restAPI.deserializador.UsuarioInstagram;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by flogog on 6/24/16.
 */
public class RestAdapter {


    public IEndpointsAPI startConnectionRestAPI(Gson gson){

        Retrofit retrofit =  new Retrofit.Builder()
        .baseUrl(ConstantsRestAPI.ROOT_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

        return retrofit.create(IEndpointsAPI.class);
    }

    public Gson createGSONDeserializerMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson createGSONDeserializerUserId(){
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new UsuarioInstagram());
        return gsonBuilder.create();
    }

    /**
     * This method will be used to call the WS previously created in heroku
     * @return retrofit
     * */
    public IEndpointsAPI startHerokuRestAPI(){

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(ConstantsRestAPI.HEROKU_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IEndpointsAPI.class);
    }


}

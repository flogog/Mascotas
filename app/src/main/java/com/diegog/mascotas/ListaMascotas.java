package com.diegog.mascotas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.adapter.MascotaPageAdapter;
import com.diegog.mascotas.fragment.MascotasFragmentPerfil;
import com.diegog.mascotas.fragment.MascotasRecyclerViewFragment;
import com.diegog.mascotas.menu.Acerca;
import com.diegog.mascotas.menu.Contacto;
import com.diegog.mascotas.menu.Instagram;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.diegog.mascotas.restAPI.model.UserResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaMascotas extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;


    private Toolbar mascotaActionBar;
    private TabLayout mascotaTabLayout;
    private ViewPager mascotaViewPager;
    public Context  context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_mascotas);

        mascotaActionBar = (Toolbar) findViewById(R.id.toolbar);
        mascotaTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mascotaViewPager = (ViewPager) findViewById(R.id.viewPager);

        mascotaActionBar.setTitle(R.string.app_name);

        context = getApplicationContext();


        setUpViewPager();

        if(mascotaActionBar!=null) {
            setSupportActionBar(mascotaActionBar);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent ;
        switch (item.getItemId()){
            case R.id.mContacto:
                intent = new Intent(this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcercaDe:
                intent = new Intent(this, Acerca.class);
                startActivity(intent);
                break;
            case R.id.mInstagram:
                intent = new Intent(this, Instagram.class);
                startActivity(intent);
                break;
            case R.id.iFavoritos:
                intent = new Intent(this, Favoritas.class);
                startActivity(intent);
                break;

            case R.id.mNotifications:
                String dispositivoID    = FirebaseInstanceId.getInstance().getToken();
                //sendHerokuToken(token);
                getInstagramId(dispositivoID);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mascota,menu);
        return true;
    }



    private ArrayList<Fragment> agregarFragmentsMascota(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MascotasRecyclerViewFragment());
        fragments.add(new MascotasFragmentPerfil());
        return fragments;
    }

    private void setUpViewPager(){
        mascotaViewPager.setAdapter(new MascotaPageAdapter(getSupportFragmentManager(),agregarFragmentsMascota()));
        mascotaTabLayout.setupWithViewPager(mascotaViewPager);
        mascotaTabLayout.getTabAt(0).setIcon(R.drawable.home_52);
        mascotaTabLayout.getTabAt(1).setIcon(R.drawable.dog_footprint_48);
    }

   /* private void sendHerokuToken(String token){
        RestAdapter restAPI =  new RestAdapter();
        IEndpointsAPI endpoints = restAPI.startHerokuRestAPI();
        Call<UserResponse> userResponseCall =   endpoints.registerHerokuTokenID(token);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                //Toast.makeText(context,userResponse.getId(),Toast.LENGTH_LONG).show();
                System.out.println("+++++++++ "+userResponse.getId());
             //   dispositivoID = userResponse.getId();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }*/

    private void getInstagramId(final String idDispositivo){
        RestAdapter     restAdapter     =  new RestAdapter();
        Gson            mediaRecentGson = restAdapter.createGSONDeserializerUserId();
        IEndpointsAPI   endpointsAPI    = restAdapter.startConnectionRestAPI(mediaRecentGson);
        SharedPreferences preferencias =  this.context.getSharedPreferences("DatosPersonales",this.context.MODE_PRIVATE);
        String usuario = preferencias.getString("Usuario",null);
        if(usuario!=null && !usuario.equals("")){
            Call<MascotaResponse>    responseCall    = endpointsAPI.getUserId(usuario);

            //System.out.println("-------------"+responseCall.toString());
            responseCall.enqueue(new Callback<MascotaResponse>() {
                @Override
                public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                    MascotaResponse mascotaResponse = response.body();
                    System.out.println("+++++++++ "+mascotaResponse.getUserId());
                    registrarUsuario(idDispositivo,mascotaResponse.getUserId());
                }

                @Override
                public void onFailure(Call<MascotaResponse> call, Throwable throwable) {
                    Toast.makeText(context,"Error en la conextion, Try Again", Toast.LENGTH_LONG).show();
                    Log.i("Error en la conextion",throwable.toString());
                }
            });
        }


    }

    private void registrarUsuario(String dispositivoID, String instagramID){
        RestAdapter         restAPI         =   new RestAdapter();
        IEndpointsAPI       endpoints       =   restAPI.startHerokuRestAPI();
        Call<UserResponse> userResponseCall =   endpoints.registerHerokuUser(dispositivoID,instagramID);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String resp = response.body().getResp();
                Toast.makeText(context,resp,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context,"Registrar Usuario Error Try Again", Toast.LENGTH_LONG).show();
                Log.i("Error en la conextion",t.toString());
            }
        });
    }


}

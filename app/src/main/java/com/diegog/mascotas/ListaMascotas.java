package com.diegog.mascotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_mascotas);
        Toolbar mascotaActionBar = (Toolbar) findViewById(R.id.mascotaActionBar);

        rvListaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvListaMascotas.setLayoutManager(llm);

        inicializaMascotas();
        inicializarAdaptador();


    }

    public void irFavoritos(View v){

        Intent intent = new Intent(this, Favoritas.class);
        intent.putExtra(getResources().getString(R.string.favoritos),getFavoriteMascotas());
        startActivity(intent);
    }

    public ArrayList<Mascota> getFavoriteMascotas(){
        ArrayList<Mascota> favoritas = new ArrayList<Mascota>();
        for(Mascota check: mascotas){
            if(check.isFavorito()){
                favoritas.add(check);
            }
        }
        return favoritas;
    }

    public void inicializarAdaptador(){
        MascotaAdapter mAdapter = new MascotaAdapter(mascotas);
        rvListaMascotas.setAdapter(mAdapter);
    }

    public void inicializaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.mnky,"Dumpy"));
        mascotas.add(new Mascota(R.drawable.cat,"Ketty"));
        mascotas.add(new Mascota(R.drawable.lion,"Liony"));
        mascotas.add(new Mascota(R.drawable.bull,"Doggy"));
        mascotas.add(new Mascota(R.drawable.panda,"Pandy"));
    }

}

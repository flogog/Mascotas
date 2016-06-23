package com.diegog.mascotas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegog.mascotas.Favoritas;
import com.diegog.mascotas.R;
import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by flogog on 6/19/16.
 */
public class MascotasFragmentPerfil extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;

    public MascotasFragmentPerfil(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_mascotas,container,false);

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotasFavorito);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);

//        GridLayoutManager glm = new GridLayoutManager(MascotasFragment.this.getContext(),4);
       // glm.setOrientation(GridLayoutManager.HORIZONTAL);

        rvListaMascotas.setLayoutManager(glm);

        inicializaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void irFavoritos(View v){

        Intent intent = new Intent(getActivity(), Favoritas.class);
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
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Chimp",2));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Chimp",5));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Lion",6));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Chimp",12));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Pand",3));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Lion",1));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Chimp",0));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Pand",5));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Pand",2));
    }


}

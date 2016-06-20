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

/**
 * Created by flogog on 6/19/16.
 */
public class MascotasFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;

    public MascotasFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_mascotas,container,false);

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        GridLayoutManager glm = new GridLayoutManager();
        glm.setOrientation(GridLayoutManager.HORIZONTAL);

        rvListaMascotas.setLayoutManager(glm);

        inicializaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador(){
        MascotaAdapter mAdapter = new MascotaAdapter(mascotas);
        rvListaMascotas.setAdapter(mAdapter);
    }

    public void inicializaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.mnky,"Chimp"));
        mascotas.add(new Mascota(R.drawable.mnky,"Chimp"));
        mascotas.add(new Mascota(R.drawable.mnky,"Lion"));
        mascotas.add(new Mascota(R.drawable.mnky,"Chimp"));
        mascotas.add(new Mascota(R.drawable.mnky,"Pand"));
    }


}

package com.diegog.mascotas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegog.mascotas.Favoritas;
import com.diegog.mascotas.R;
import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.pojo.Mascota;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasRecyclerViewFragment extends Fragment implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;

    public MascotasRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mascotas_recycler_view,container,false);

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvListaMascotas.setLayoutManager(llm);

        inicializaMascotas();
        inicializarAdaptador();
        // Inflate the layout for this fragmen
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
        mascotas.add(new Mascota(R.drawable.rsz_1mnky,"Chimp",0));
        mascotas.add(new Mascota(R.drawable.rsz_1cat,"Kitty",0));
        mascotas.add(new Mascota(R.drawable.rsz_1lion,"Lion",0));
        mascotas.add(new Mascota(R.drawable.rsz_1bull,"Doggy",0));
        mascotas.add(new Mascota(R.drawable.rsz_3panda,"Pand",0));
    }


}

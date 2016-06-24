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
import com.diegog.mascotas.presentador.IRVFragmentPresenter;
import com.diegog.mascotas.presentador.RVFragmentPresenter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasRecyclerViewFragment extends Fragment implements IMascotasRecyclerViewFragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;
    private IRVFragmentPresenter iRVFPresenter;

    public MascotasRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mascotas_recycler_view,container,false);

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        iRVFPresenter = new RVFragmentPresenter(this,getContext());

        return v;
    }

    public void irFavoritos(View v){

        Intent intent = new Intent(getActivity(), Favoritas.class);
        startActivity(intent);
    }


    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm);


    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdapter mAdapter = new MascotaAdapter(mascotas);
        return mAdapter;
    }

    @Override
    public void inicializaAdaptadorRV(MascotaAdapter mascotaAdapter) {
        rvListaMascotas.setAdapter(mascotaAdapter);
    }
}

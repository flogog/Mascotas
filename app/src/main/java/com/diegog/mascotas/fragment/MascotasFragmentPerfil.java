package com.diegog.mascotas.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegog.mascotas.R;
import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.adapter.PerfilAdapter;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.presentador.IRVFragmentPresenter;
import com.diegog.mascotas.presentador.IRVFragmentPresenterFav;
import com.diegog.mascotas.presentador.IRVFragmentPresenterPerfil;
import com.diegog.mascotas.presentador.RVFragmentPresenter;
import com.diegog.mascotas.presentador.RVFragmentPresenterFav;
import com.diegog.mascotas.presentador.RVFragmentPresenterPerfil;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by flogog on 6/19/16.
 */
public class MascotasFragmentPerfil extends Fragment implements IPerfilRecyclerViewFragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;
    private IRVFragmentPresenterPerfil iRVFPresenter;
    private CircularImageView covProfilePic;
    private String profilePicURL;

    public MascotasFragmentPerfil(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_mascotas,container,false);

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotasFavorito);
        //iRVFPresenter = new RVFragmentPresenter(this,getContext());

        iRVFPresenter = new RVFragmentPresenterPerfil(this,getContext());

        covProfilePic = (CircularImageView) v.findViewById(R.id.civProfilePic);

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm);


    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getContext(),2);
        rvListaMascotas.setLayoutManager(glm);
    }

    @Override
    public PerfilAdapter createAdapter(ArrayList<Mascota> mascotas) {
        PerfilAdapter pAdapter = new PerfilAdapter(mascotas,getActivity());
        return pAdapter;
    }

    @Override
    public void inicializaAdaptadorRV(PerfilAdapter perfilAdapter) {
        rvListaMascotas.setAdapter(perfilAdapter);
    }

    @Override
    public void sendProfilePic(String profilePicURL) {

        this.profilePicURL = profilePicURL;
        Picasso.with(getActivity())
                .load(this.profilePicURL)
                .placeholder(R.drawable.bull)
                .into(covProfilePic);
    }


}

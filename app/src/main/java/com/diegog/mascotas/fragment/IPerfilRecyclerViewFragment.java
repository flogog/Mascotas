package com.diegog.mascotas.fragment;


import com.diegog.mascotas.adapter.PerfilAdapter;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by flogog on 6/23/16.
 */
public interface IPerfilRecyclerViewFragment {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public PerfilAdapter createAdapter(ArrayList<Mascota> mascotas);

    public void inicializaAdaptadorRV(PerfilAdapter perfilAdapter);

    public void sendProfilePic(String profilePicURL);

}

package com.diegog.mascotas.fragment;


import android.view.View;

import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by flogog on 6/23/16.
 */
public interface IMascotasRecyclerViewFragment {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas);

    public void inicializaAdaptadorRV(MascotaAdapter mascotaAdapter);

}

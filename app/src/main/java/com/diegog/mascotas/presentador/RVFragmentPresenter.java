package com.diegog.mascotas.presentador;

import android.content.Context;

import com.diegog.mascotas.database.ConstructorMascotas;
import com.diegog.mascotas.fragment.IMascotasRecyclerViewFragment;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by flogog on 6/23/16.
 */
public class RVFragmentPresenter implements IRVFragmentPresenter {

    private IMascotasRecyclerViewFragment iMascotasRVFragment;
    private Context                 context;
    private ConstructorMascotas     constructorMascotas;
    private ArrayList<Mascota>      mascotas;

    public RVFragmentPresenter(IMascotasRecyclerViewFragment iMascotasRVFragment, Context context) {
        this.iMascotasRVFragment   =   iMascotasRVFragment;
        this.context                =   context;
        getMascotasBD();
    }

    @Override
    public void getMascotasBD() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.getMascotas();
        showMascotasRV();
    }

    @Override
    public void showMascotasRV() {
        iMascotasRVFragment.inicializaAdaptadorRV(iMascotasRVFragment.createAdapter(mascotas));
        iMascotasRVFragment.generarLinearLayoutVertical();
    }
}

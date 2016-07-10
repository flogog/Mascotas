package com.diegog.mascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.diegog.mascotas.database.ConstructorMascotas;
import com.diegog.mascotas.fragment.IMascotasRecyclerViewFragment;
import com.diegog.mascotas.fragment.IPerfilRecyclerViewFragment;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flogog on 6/23/16.
 */
public class RVFragmentPresenterFav implements IRVFragmentPresenterFav {

    private IMascotasRecyclerViewFragment iMascotasRVFragment;
    private Context                 context;
    private ConstructorMascotas     constructorMascotas;
    private ArrayList<Mascota>      mascotas;

    public RVFragmentPresenterFav(IMascotasRecyclerViewFragment iMascotasRVFragment, Context context) {
        this.iMascotasRVFragment   =   iMascotasRVFragment;
        this.context                =   context;
        getMascotasBD();
    }

    @Override
    public void getMascotasBD() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.getMascotasFav();
        showMascotasRV();
    }

    @Override
    public void showMascotasRV() {
        iMascotasRVFragment.inicializaAdaptadorRV(iMascotasRVFragment.createAdapter(mascotas));
        iMascotasRVFragment.generarLinearLayoutVertical();
    }
}

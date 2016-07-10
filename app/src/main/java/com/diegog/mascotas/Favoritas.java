package com.diegog.mascotas;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.fragment.IMascotasRecyclerViewFragment;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.presentador.IRVFragmentPresenter;
import com.diegog.mascotas.presentador.IRVFragmentPresenterFav;
import com.diegog.mascotas.presentador.RVFragmentPresenter;
import com.diegog.mascotas.presentador.RVFragmentPresenterFav;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity implements IMascotasRecyclerViewFragment {

    private RecyclerView rvListaMascotas;
    private IRVFragmentPresenterFav iRVFPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritas);
        Toolbar mascotaActionBar = (Toolbar) findViewById(R.id.mascotaActionBar);
        mascotaActionBar.setSaveFromParentEnabled(true);
        //Habilita boton atras
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mascotaActionBar.setTitle(R.string.fav_title);
        mascotaActionBar.setLogo(R.drawable.dog_bone_52);
        mascotaActionBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        mascotaActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(Favoritas.this);
            }
        });


        rvListaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFavorito);
        iRVFPresenter = new RVFragmentPresenterFav(this, getBaseContext());

    }


    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm);


    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(this,2);
        rvListaMascotas.setLayoutManager(glm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdapter mAdapter = new MascotaAdapter(mascotas, this);
        return mAdapter;
    }

    @Override
    public void inicializaAdaptadorRV(MascotaAdapter mascotaAdapter) {
        rvListaMascotas.setAdapter(mascotaAdapter);
    }
}

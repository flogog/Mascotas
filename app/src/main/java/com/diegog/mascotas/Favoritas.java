package com.diegog.mascotas;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.fragment.MascotasRecyclerViewFragment;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritas);
        Toolbar mascotaActionBar = (Toolbar) findViewById(R.id.mascotaActionBar);
        mascotaActionBar.setSaveFromParentEnabled(true);
        //Habilita boton atras
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mascotaActionBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        mascotaActionBar.setTitle(R.string.app_name);
        mascotaActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(Favoritas.this);
            }
        });


        rvListaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFavorito);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvListaMascotas.setLayoutManager(llm);

        Bundle param    =   getIntent().getExtras();

        ArrayList<Fragment> fragments = (ArrayList<Fragment>) param.getSerializable(getResources().getString(R.string.favoritos));
        mascotas   = ((MascotasRecyclerViewFragment) fragments.get(0)).getFavoriteMascotas();

        inicializarAdaptador();
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

}

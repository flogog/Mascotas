package com.diegog.mascotas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.diegog.mascotas.adapter.MascotaAdapter;
import com.diegog.mascotas.adapter.MascotaPageAdapter;
import com.diegog.mascotas.fragment.MascotasFragment;
import com.diegog.mascotas.fragment.MascotasRecyclerViewFragment;
import com.diegog.mascotas.menu.Acerca;
import com.diegog.mascotas.menu.Contacto;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {


    private Toolbar mascotaActionBar;
    private TabLayout mascotaTabLayout;
    private ViewPager mascotaViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_mascotas);

        mascotaActionBar = (Toolbar) findViewById(R.id.toolbar);
        mascotaTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mascotaViewPager = (ViewPager) findViewById(R.id.viewPager);

        mascotaActionBar.setTitle(R.string.app_name);


        setUpViewPager();

        if(mascotaActionBar!=null) {
            setSupportActionBar(mascotaActionBar);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent ;
        switch (item.getItemId()){
            case R.id.mContacto:
                intent = new Intent(this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcercaDe:
                intent = new Intent(this, Acerca.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mascota,menu);
        return true;
    }



    private ArrayList<Fragment> agregarFragmentsMascota(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MascotasRecyclerViewFragment());
        fragments.add(new MascotasFragment());
        return fragments;
    }

    private void setUpViewPager(){
        mascotaViewPager.setAdapter(new MascotaPageAdapter(getSupportFragmentManager(),agregarFragmentsMascota()));
        mascotaTabLayout.setupWithViewPager(mascotaViewPager);
        mascotaTabLayout.getTabAt(0).setIcon(R.drawable.home_52);
        mascotaTabLayout.getTabAt(1).setIcon(R.drawable.dog_footprint_48);
    }

}

package com.diegog.mascotas.menu;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.diegog.mascotas.ListaMascotas;
import com.diegog.mascotas.R;

import java.io.FileOutputStream;

/**
 * Created by flogog on 6/26/16.
 */
public class Instagram extends AppCompatActivity {

    private ImageButton botonEnviar;
    private TextInputLayout etUsuario;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        //setSupportActionBar(mascotaActionBar);
        Toolbar mascotaActionBar = (Toolbar) findViewById(R.id.mascotaActionBar);
        mascotaActionBar.setSaveFromParentEnabled(true);
        mascotaActionBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mascotaActionBar.setTitle(R.string.app_name);
        mascotaActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo(Instagram.this, Instagram.this.getIntent());
            }
        });


        botonEnviar = (ImageButton) findViewById(R.id.etGuardarCuenta);

        botonEnviar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                guardarPreferencia(v);
                finish();

                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);

            }
        });

    }

    public void guardarPreferencia(View v){
        SharedPreferences preferencias =  getSharedPreferences("DatosPersonales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        etUsuario = (TextInputLayout) findViewById(R.id.etUser);
        usuario = etUsuario.getEditText().getText().toString().trim();
        editor.putString("Usuario",usuario);

        editor.apply();

        Toast.makeText(Instagram.this, "Usuario Alamcenado", Toast.LENGTH_SHORT).show();
        etUsuario.getEditText().setText("");
    }




}

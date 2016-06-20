package com.diegog.mascotas.menu;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diegog.mascotas.R;

public class Contacto extends AppCompatActivity {

    private EditText etCorreo;
    private EditText etNombre;
    private EditText etMensaje;
    private Button botonEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        //setSupportActionBar(mascotaActionBar);
        Toolbar mascotaActionBar = (Toolbar) findViewById(R.id.mascotaActionBar);
        mascotaActionBar.setSaveFromParentEnabled(true);
        mascotaActionBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mascotaActionBar.setTitle(R.string.app_name);
        mascotaActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo(Contacto.this, Contacto.this.getIntent());
            }
        });

        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etMensaje = (EditText) findViewById(R.id.etMensaje);

        botonEnviar = (Button) findViewById(R.id.etBoton);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Contacto.this,"Mensaje Enviado",Toast.LENGTH_SHORT).show();

            }
        });

    }
    
}

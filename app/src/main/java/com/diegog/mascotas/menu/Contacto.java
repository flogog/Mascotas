package com.diegog.mascotas.menu;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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

    private FloatingActionButton botonEnviar;


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


        botonEnviar = (FloatingActionButton) findViewById(R.id.etBoton);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            private TextInputLayout etNombre, etCorreo, etComentario;

            @Override
            public void onClick(View v) {

                etNombre = (TextInputLayout)findViewById(R.id.etNombre);
                etCorreo = (TextInputLayout)findViewById(R.id.etCorreo);
                etComentario = (TextInputLayout)findViewById(R.id.etMensaje);

                String nombre = etNombre.getEditText().getText().toString().trim();
                String correo = etCorreo.getEditText().getText().toString().trim();
                String comentario = etComentario.getEditText().getText().toString().trim();

                Intent sendEmail = new Intent(Intent.ACTION_SEND);
                sendEmail.setType("plain/text");
                sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
                sendEmail.putExtra(Intent.EXTRA_TEXT, comentario);
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Mensaje enviado desde APP por " + nombre);
                startActivity(Intent.createChooser(sendEmail, "Elige una aplicación: "));
                Toast.makeText(Contacto.this,"Mensaje Enviado",Toast.LENGTH_SHORT).show();

            }
        });

    }

}

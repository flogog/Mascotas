package com.diegog.mascotas.menu;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diegog.mascotas.R;

public class Acerca extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        //setSupportActionBar(mascotaActionBar);
        Toolbar mascotaActionBar = (Toolbar) findViewById(R.id.mascotaActionBar);
        mascotaActionBar.setSaveFromParentEnabled(true);
        mascotaActionBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mascotaActionBar.setTitle(R.string.app_name);
        mascotaActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo(Acerca.this, Acerca.this.getIntent());
            }
        });
    }
}

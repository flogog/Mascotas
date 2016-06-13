package com.diegog.mascotas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by flogog on 6/12/16.
 */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;

    public MascotaAdapter(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);

        holder.imgFotoMascotaCV.setImageResource(mascota.getFoto());
        holder.tvNombreMascotaCV.setText(mascota.getNombre());
        holder.ivImgFavCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota.setFavorito(true);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView   imgFotoMascotaCV;
        private TextView    tvNombreMascotaCV;
        private ImageView   ivImgFavCV;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFotoMascotaCV    = (ImageView)   itemView.findViewById(R.id.ivImgMascotaCV);
            tvNombreMascotaCV   = (TextView)    itemView.findViewById(R.id.tvNombreMascotaCV);
            ivImgFavCV          = (ImageView)   itemView.findViewById(R.id.ivImgFavCV);


        }

    }
}

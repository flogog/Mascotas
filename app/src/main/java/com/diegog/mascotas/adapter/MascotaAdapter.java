package com.diegog.mascotas.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegog.mascotas.R;
import com.diegog.mascotas.database.ConstructorMascotas;
import com.diegog.mascotas.pojo.Mascota;

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
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);

        holder.imgFotoMascotaCV.setImageResource(mascota.getFoto());
        holder.tvNombreMascotaCV.setText(mascota.getNombre());
        holder.tvFavCV.setText(String.valueOf(mascota.getFav())+" Likes");
        holder.ivImgFavCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstructorMascotas constMascotas = new ConstructorMascotas(v.getContext());
                constMascotas.likeMascota(mascota);
                holder.tvFavCV.setText(String.valueOf(constMascotas.getLikeMascota(mascota))+" Likes");
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
        private TextView    tvFavCV;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFotoMascotaCV    = (ImageView)   itemView.findViewById(R.id.ivImgMascotaCV);
            tvNombreMascotaCV   = (TextView)    itemView.findViewById(R.id.tvNombreMascotaCV);
            ivImgFavCV          = (ImageView)   itemView.findViewById(R.id.ivImgFavCV);
            tvFavCV             = (TextView)    itemView.findViewById(R.id.tvFavCV);


        }

    }
}

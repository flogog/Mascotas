package com.diegog.mascotas.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegog.mascotas.R;
import com.diegog.mascotas.database.ConstructorMascotas;
import com.diegog.mascotas.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by flogog on 6/26/16.
 */
public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>{
    ArrayList<Mascota> mascotas;

    private Activity activity;

    public PerfilAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_insta_pet, parent, false);
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PerfilViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrl())
                .placeholder(R.drawable.bull)
                .into(holder.imgFotoMascotaCV);
        //holder.imgFotoMascotaCV.setImageResource(mascota.getFoto());
        holder.tvFavCV.setText(String.valueOf(mascota.getFav())+" Likes");

    }

    @Override
    public int getItemCount() {

        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {

        private ImageView   imgFotoMascotaCV;
        private ImageView   imgLikeCV;
        private TextView    tvFavCV;

        public PerfilViewHolder(View itemView) {
            super(itemView);

            imgFotoMascotaCV    = (ImageView)   itemView.findViewById(R.id.ivImgMascotaCV);
            imgLikeCV           = (ImageView)   itemView.findViewById(R.id.ivImgLikeCV);
            tvFavCV             = (TextView)    itemView.findViewById(R.id.tvFavCV);

        }

    }
}


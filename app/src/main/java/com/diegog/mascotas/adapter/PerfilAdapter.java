package com.diegog.mascotas.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diegog.mascotas.R;
import com.diegog.mascotas.database.ConstructorMascotas;
import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.restAPI.model.UserResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flogog on 6/26/16.
 */
public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>{
    ArrayList<Mascota> mascotas;

    private Activity activity;
    private Context context;

    public PerfilAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
        this.context  = activity.getApplicationContext();
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

        holder.imgLikeCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dispositivoID    = FirebaseInstanceId.getInstance().getToken();
                registrarInstagramLike(mascota.getIdLike(),dispositivoID,mascota.getId());
            }
        });

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

    private void registrarInstagramLike(String foto, String dispositivo, String usuario){
        RestAdapter restAPI         =   new RestAdapter();
        IEndpointsAPI endpoints       =   restAPI.startHerokuRestAPI();
        Call<UserResponse> userResponseCall =   endpoints.registerLike(foto,dispositivo,usuario);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String resp = response.body().getResp();
                Toast.makeText(context,resp,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context,"Registrar Usuario Error Try Again", Toast.LENGTH_LONG).show();
                Log.i("Error en la conextion",t.toString());
            }
        });
    }
}


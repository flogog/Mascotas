package com.diegog.mascotas.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.diegog.mascotas.ListaMascotas;
import com.diegog.mascotas.R;
import com.diegog.mascotas.fragment.MascotasFragmentPerfil;
import com.diegog.mascotas.presentador.RVFragmentPresenterPerfil;
import com.diegog.mascotas.restAPI.IEndpointsAPI;
import com.diegog.mascotas.restAPI.adapter.RestAdapter;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.diegog.mascotas.restAPI.model.UserResponse;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flogog on 7/20/16.
 */
public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;
    private static String username;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Intent profileIntent =  new Intent(this, ListaMascotas.class);
        PendingIntent pendingIntentP =   PendingIntent.getActivity(this,0,profileIntent,PendingIntent.FLAG_ONE_SHOT);

        Intent followIntent =  new Intent();
        followIntent.setAction("FOLLOW_UNFOLLOW");
        followIntent.putExtra("userid",remoteMessage.getNotification().getTitle());
        followIntent.putExtra("action","follow");
        PendingIntent pendingIntentF =   PendingIntent.getBroadcast(this,0,followIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent targetIntent =  new Intent();
        targetIntent.setAction("TARGET");
        targetIntent.putExtra("userid",remoteMessage.getNotification().getTitle());
        PendingIntent pendingIntentT =   PendingIntent.getBroadcast(this,0,targetIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        Uri sound   = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action profile =
                new NotificationCompat.Action.Builder(R.drawable.prof,
                        "Ver Perfil", pendingIntentP)
                        .build();

        NotificationCompat.Action follow =
                new NotificationCompat.Action.Builder(R.drawable.follow_button,
                        "Follow", pendingIntentF)
                        .build();

        NotificationCompat.Action targetProfile =
                new NotificationCompat.Action.Builder(R.drawable.insta,
                        "ir a perfil de quien raiteo tu foto", pendingIntentT)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.pets))
                        .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.lion_statue_48)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntentP)
                .extend(wearableExtender.addAction(profile))
                .extend(wearableExtender.addAction(follow))
                .extend(wearableExtender.addAction(targetProfile));


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        Log.d(TAG,"From:"+remoteMessage.getFrom());
        Log.d(TAG,"Notification Message Body:"+remoteMessage.getNotification().getBody());

    }


    public void followInstagramUser(String userId,String action){

        RestAdapter         restAPI          =   new RestAdapter();
        IEndpointsAPI       endpoints        =   restAPI.startInstaRestAPI();
        Call<UserResponse>  userResponseCall =   endpoints.followUserID(userId,action);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String resp = response.body().getResp();
                System.out.println("RESPUESTA FOLLOW "+resp);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Error en la conextion",t.toString());
            }
        });
    }
}

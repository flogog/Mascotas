package com.diegog.mascotas.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.diegog.mascotas.ListaMascotas;
import com.diegog.mascotas.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by flogog on 7/20/16.
 */
public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "FIREBASE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Intent i =  new Intent(this, ListaMascotas.class);
        PendingIntent pendingIntent =   PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        Uri sound   = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.lion_statue_48)
                .setContentTitle("Remote Message")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent);

        NotificationManager notification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification.notify(0,builder.build());

        Log.d(TAG,"From:"+remoteMessage.getFrom());
        Log.d(TAG,"Notification Message Body:"+remoteMessage.getNotification().getBody());
    }
}

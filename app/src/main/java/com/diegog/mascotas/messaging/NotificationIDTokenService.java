package com.diegog.mascotas.messaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by flogog on 7/20/16.
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {

    public static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        String token    = FirebaseInstanceId.getInstance().getToken();
        sendToken(token);
    }

    private void sendToken(String token){
        Log.d(TAG,token);
    }
}

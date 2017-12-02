package com.application.aayush.geeta;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Aayush on 11/12/2017.
 */

public class RingtonePlayService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent){

        Log.e("In Start Command","On Start Service");
        return START_NOT_STICKY;
    }
}

package com.application.aayush.geeta;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Aayush on 11/12/2017.
 */

public class RingtonePlayService extends Service {
    MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent,int flags,int startid){

        Log.e("Local Service","Received start id"+startid+":"+intent);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}

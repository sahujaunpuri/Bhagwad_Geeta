package com.application.aayush.geeta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Aayush on 11/12/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In Start Service","Alarm Receiver");
        Intent intent1 = new Intent(context,RingtonePlayService.class);
        context.startService(intent1);

    }
}

package com.application.aayush.geeta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Aayush on 11/12/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences;
    String alarmSound;
    MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In Start Service","Alarm Receiver");
        sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        mediaPlayer = new MediaPlayer();
        if(sharedPreferences.getBoolean("vibrate_flag",false)) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(10000);
        }
        alarmSound = sharedPreferences.getString("alarm_sound","null");
        Uri mp3 = Uri.parse("android.resource://"+context.getPackageName()+"/raw/"+alarmSound);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(context,mp3);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent1 = new Intent(context,RingtonePlayService.class);
        context.startService(intent1);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
    }
}

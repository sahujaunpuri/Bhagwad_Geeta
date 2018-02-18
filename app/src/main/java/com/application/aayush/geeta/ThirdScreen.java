package com.application.aayush.geeta;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Aayush on 4/29/2017.
 */

public class ThirdScreen extends AppCompatActivity {
    TextView heading,content,getStarted;
    public boolean dialogflag = false;
    private GestureDetectorCompat gestureDetectorCompat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdscreen);
        heading = (TextView)findViewById(R.id.textView);
        content = (TextView)findViewById(R.id.textView16);
        getStarted = (TextView) findViewById(R.id.textView24);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");
        heading.setTypeface(customFont);
        content.setTypeface(customFont);
        getStarted.setTypeface(customFont);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    readData();
                    finish();
                    dialogflag = true;
                    startActivity(new Intent(ThirdScreen.this,MyProfile.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    // Use TaskStackBuilder to build the back stack and get the PendingIntent
                    TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(ThirdScreen.this);
                    // add all of DetailsActivity's parents to the stack,
                    // followed by DetailsActivity itself

                    taskStackBuilder.addNextIntentWithParentStack(new Intent(ThirdScreen.this,MyProfile.class));
                    PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        gestureDetectorCompat = new GestureDetectorCompat(this,new MyGestureListener());

    }

    protected void readData() throws IOException, JSONException {
        InputStream inputStream = getAssets().open("shloka_details.json");
        Scanner sc = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while(sc.hasNextLine()){
            builder.append(sc.nextLine());
        }
        parseJSON(builder.toString());
    }
    private void parseJSON(String s ) throws JSONException {
        String string1="",string2="",string3="" ;

        JSONObject root = new JSONObject(s);
        JSONObject shloka = root.getJSONObject("Geeta_Shlokas");//define array
        JSONArray items = shloka.getJSONArray("shlokas");
        for(int i = 0;i<items.length();i++){
            JSONObject item = items.getJSONObject(i);
            System.out.print(items.length());
            string1 = item.getString("verse");
            string2 = item.getString("translation");
            string3 = item.getString("purpose");
            DataBaseHandlerShloka db = new DataBaseHandlerShloka(this);
            db.addShloka(new Shlokas(string1,string2,string3));

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.gestureDetectorCompat.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            return true;
        }
    }
}
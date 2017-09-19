package com.application.aayush.geeta;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Aayush on 4/29/2017.
 */

public class ThirdScreen extends AppCompatActivity {
    TextView heading,content,getStarted;
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
                    finish();
                    startActivity(new Intent(ThirdScreen.this,SignUp.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        gestureDetectorCompat = new GestureDetectorCompat(this,new MyGestureListener());

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
package com.application.aayush.geeta;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//removes the notofication bar
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.text1);
        textView2 = (TextView)findViewById(R.id.textView27);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Hairline.ttf");

        textView1.setTypeface(custom_font);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this,FirstScreen.class));
                /*//to finish the animation
                finish();*/

            }
        }, 3000);
        // Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down_in);
       // textView.setAnimation(animation);
        /*animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/
    }
}
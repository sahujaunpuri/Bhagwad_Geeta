package com.application.aayush.geeta;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Aayush on 5/1/2017.
 */

public class SignUp extends AppCompatActivity {
    Button google,facebook,otp,submit;
    EditText editText;
    TextView registration;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        google = (Button)findViewById(R.id.button2);
        facebook = (Button)findViewById(R.id.button4);
        otp = (Button)findViewById(R.id.button5);
        editText = (EditText)findViewById(R.id.editText);
        submit = (Button)findViewById(R.id.button7);
        registration = (TextView)findViewById(R.id.textView4);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Bold.ttf");
        registration.setTypeface(customFont);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    finish();
                    startActivity(new Intent(SignUp.this,MyProfile.class));
                   overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        if(editText.getText().toString().equals("7435050906")){
            Toast.makeText(getApplicationContext(),"Phone number verified",Toast.LENGTH_SHORT).show();
            try{
                finish();
                startActivity(new Intent(SignUp.this,MyProfile.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Phone number ",Toast.LENGTH_SHORT).show();
        }


    }
}

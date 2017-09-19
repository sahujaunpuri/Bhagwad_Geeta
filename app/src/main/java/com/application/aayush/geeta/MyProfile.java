package com.application.aayush.geeta;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


/**
 * Created by Aayush on 5/6/2017.
 */

public class MyProfile extends AppCompatActivity {
   Button save;
    EditText name,mobileNumber,email,address,city;
    Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final DatabaseHandler db = new DatabaseHandler(this);
        bundle = new Bundle();
        name = (EditText)findViewById(R.id.input_name);
        mobileNumber = (EditText)findViewById(R.id.input_mobile_number);
        email = (EditText)findViewById(R.id.input_email_id);
        address = (EditText)findViewById(R.id.input_address);
        city = (EditText)findViewById(R.id.input_city);
        bundle.putString("name",name.getText().toString());
        bundle.putString("mobile_number",mobileNumber.getText().toString());
        bundle.putString("email",email.getText().toString());
        bundle.putString("address",address.getText().toString());
        bundle.putString("city",city.getText().toString());
        save = (Button)findViewById(R.id.btn_signup);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                //Inserting user data

                Log.d("Insert","Inserting...");
                db.addUser(new UserProfile(name.getText().toString(),mobileNumber.getText().toString(),email.getText().toString(),address.getText().toString(),city.getText().toString()));
                //db.addUser(new UserProfile(name.getText().toString(),mobileNumber.getText().toString(),email.getText().toString(),address.getText().toString(),city.getText().toString()));
                Intent intent = new Intent(MyProfile.this,UserMenu.class);
                intent.putExtra("user_name",name.getText().toString());
                intent.putExtra("user_mobilenumber",mobileNumber.getText().toString());
                intent.putExtra("user_email",email.getText().toString());
                intent.putExtra("user_address",address.getText().toString());
                intent.putExtra("user_city",city.getText().toString());
                startActivity(intent);

                Log.d("Reading","Reading...");
                List<UserProfile>  profileList = db.getAllUsers();
                for (UserProfile up:profileList){
                    String log = "ID:"+up.getUser_id()+",Name:"+up.getName()+",Phone Number:"+up.getMobile_no()+",Email ID:"+up.getEmail_id()+",Address:"+up.getAddress()+",City:"+up.getCity();
                    //writing users to the list
                    Log.d("Entry:",log);
                }

           }
        });
    }
}

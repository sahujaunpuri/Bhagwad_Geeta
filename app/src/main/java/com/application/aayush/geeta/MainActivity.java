package com.application.aayush.geeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2;
    boolean flag = false;
    DataBaseHandlerShloka db;
    DatabaseHandler user;
    int count = 0;
    SQLiteDatabase db1;
    public static final String DEFAULT = "N/A";
    Bundle bundle;
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
        if (flag == false){
            //Inserting Shlokas

               Log.d("Insert","Inserting Shlokas...");
                try {
                    readData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            flag = true;
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                user = new DatabaseHandler(getApplicationContext());
               // user.deleteUser(new UserProfile(0,"","","","",""));
                int count = user.userCount();
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                int count1 = sharedPreferences.getAll().size();
                if(count1 == 0){
                   startActivity(new Intent(MainActivity.this, FirstScreen.class));
                }
                else {
                    System.out.println("count="+count1);
                    Intent intent = new Intent(MainActivity.this,UserMenu.class);
                    intent.putExtra("user_name",sharedPreferences.getString("name",DEFAULT));
                    intent.putExtra("user_mobilenumber",sharedPreferences.getString("mobile_no",DEFAULT));
                    intent.putExtra("user_email",sharedPreferences.getString("email_id",DEFAULT));
                    intent.putExtra("user_address",sharedPreferences.getString("address",DEFAULT));
                    intent.putExtra("user_city",sharedPreferences.getString("city",DEFAULT));
                    Toast.makeText(MainActivity.this,sharedPreferences.getString("name",DEFAULT),Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
            }
        }, 3000);

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
}
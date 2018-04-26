package com.application.aayush.geeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aayush on 5/1/2017.
 */

public class SignUp extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    Button otp,submit;
//    private SignInButton google;
    LoginButton facebook;
    EditText editText;
    TextView registration;
    private CallbackManager callbackManager;
    SharedPreferences sharedPreferences;
    private GoogleApiClient mGoogleApiClient;

    String n = "",m = "",e = "",a = "",c = "",defaultValue = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        /*mGoogleApiClient = new GoogleApiClient.Builder(SignUp.this)
                .enableAutoManage(SignUp.this , this )
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
*/


        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        n = sharedPreferences.getString("name",defaultValue);
        m = sharedPreferences.getString("mobile_no",defaultValue);
        e = sharedPreferences.getString("email_id",defaultValue);
        a = sharedPreferences.getString("address",defaultValue);
        c = sharedPreferences.getString("city",defaultValue);
        setContentView(R.layout.activity_signup);
        callbackManager = CallbackManager.Factory.create();
  //      google = (Button)findViewById(R.id.button2);
        facebook = (LoginButton) findViewById(R.id.button4);
        facebook.setBackgroundResource(R.mipmap.facebook);
        facebook.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        otp = (Button)findViewById(R.id.button5);
        editText = (EditText)findViewById(R.id.editText);
        submit = (Button)findViewById(R.id.button7);
        registration = (TextView)findViewById(R.id.textView4);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Bold.ttf");
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
        registration.setTypeface(customFont);
/*        otp.setOnClickListener(new View.OnClickListener() {
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
        });*/

        facebook.setReadPermissions(permissionNeeds);


        facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(SignUp.this, String.valueOf(loginResult.getAccessToken()), Toast.LENGTH_SHORT).show();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {
                                    String x = String.valueOf(object.getString("name"));
                                    Log.d("Hi, ", x );
/*
                                    Intent intent = new Intent(SignUp.this,UserMenu.class);
                                    intent.putExtra("user_name",n);
                                    intent.putExtra("user_mobilenumber",m);
                                    intent.putExtra("user_email",e);
                                    intent.putExtra("user_address",a);
                                    intent.putExtra("user_city",c);
                                    finish();
                                    startActivity(intent);
*/
                                } catch(JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();            }

            @Override
            public void onCancel() {
                Toast.makeText(SignUp.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Log.v("LoginActivity", e.getCause().toString());
            }

        });

/*        if(editText.getText().toString().equals("7435050906")){
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
        }*/



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1) {
            Intent intent = new Intent(SignUp.this, UserMenu.class);
            intent.putExtra("user_name", n);
            intent.putExtra("user_mobilenumber", m);
            intent.putExtra("user_email", e);
            intent.putExtra("user_address", a);
            intent.putExtra("user_city", c);
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
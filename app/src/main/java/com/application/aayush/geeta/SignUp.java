package com.application.aayush.geeta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aayush on 5/1/2017.
 */

public class SignUp extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    Button otp,submit;
    private SignInButton google;
    LoginButton facebook;
    TextView registration,tv;
    private CallbackManager callbackManager;
    SharedPreferences sharedPreferences,sharedPreferences2;
    private FirebaseAuth mauth;
    FirebaseAuth.AuthStateListener authStateListener;
    private final int RC_SIGN_IN = 2;
    GoogleApiClient mGoogleApiClient;
    EditText phone_number;
    String n = "",m = "",e = "",a = "",c = "",defaultValue = "",contact_number = "" , mVerificationId = "" , formattedDate = "";
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    int btnType = 0;
    SimpleDateFormat sdf ;
    SharedPreferences.Editor editor ;
    Date date = null;
    @Override
    protected void onStart() {
        super.onStart();
        mauth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter("otp"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(SignUp.this).unregisterReceiver(receiver);
    }
    BroadcastReceiver receiver = new BroadcastReceiver() {
        String numbers = "";
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                numbers = message.replaceAll("[^0-9]", "");
                tv = (TextView) findViewById(R.id.txtview);
                tv.setText(numbers);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(SignUp.this)
                .enableAutoManage(SignUp.this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        mauth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(SignUp.this, UserMenu.class);
                    intent.putExtra("user_name", n);
                    intent.putExtra("user_mobilenumber", m);
                    intent.putExtra("user_email", e);
                    intent.putExtra("user_address", a);
                    intent.putExtra("user_city", c);
                    /*finish(); commenting*/
                    updateAccessFlagForShloka(1);
                    startActivity(intent);
                    finish();
                }
            }
        };
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("app_data", Context.MODE_PRIVATE);
        editor = sharedPreferences2.edit();
        sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        date = Calendar.getInstance().getTime();
        formattedDate = sdf.format(date);
        n = sharedPreferences.getString("name",defaultValue);
        m = sharedPreferences.getString("mobile_no",defaultValue);
        e = sharedPreferences.getString("email_id",defaultValue);
        a = sharedPreferences.getString("address",defaultValue);
        c = sharedPreferences.getString("city",defaultValue);
        setContentView(R.layout.activity_signup);
        callbackManager = CallbackManager.Factory.create();
        google = (SignInButton) findViewById(R.id.button2);
        facebook = (LoginButton) findViewById(R.id.button4);
        facebook.setBackgroundResource(R.mipmap.facebook);
        facebook.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        otp = (Button)findViewById(R.id.button5);
        phone_number = (EditText)findViewById(R.id.editText);
        submit = (Button)findViewById(R.id.button7);
        registration = (TextView)findViewById(R.id.textView4);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Bold.ttf");
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
        registration.setTypeface(customFont);
        customizeGoogleButton(google);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnType == 0) {

                    contact_number = "+91"+phone_number.getText().toString();
                    phone_number.setEnabled(false);
                    otp.setEnabled(false);
                    submit.setEnabled(false);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            contact_number, //phone number to verify
                            60, //Timeout duration
                            TimeUnit.SECONDS,
                            SignUp.this,
                            mCallBacks
                    );
                }
                else{
                        otp.setEnabled(false);
                        submit.setEnabled(false);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,tv.getText().toString() );
                    signInWithPhoneAuthCredential(credential);




                }
            }
        });
        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SignUp.this, "There was an error in verification", Toast.LENGTH_SHORT).show();
                phone_number.setEnabled(true);
            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("TAG", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                btnType = 1;
                otp.setEnabled(true);
                submit.setEnabled(false);
                // [START_EXCLUDE]
                // Update UI

                // [END_EXCLUDE]
            }
        };
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
            /*finish();* commenting*/
            updateAccessFlagForShloka(1);
            startActivity(intent);
            finish();
        }
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        else if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if ( googleSignInResult.isSuccess()){
                //Google Signin was successfull ,authenticate with firebase
                GoogleSignInAccount account = googleSignInResult.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else
            {
                Toast.makeText(this, "Auth went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mauth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //sign is success ,update UI with user's signed in interface
                            Log.d("TAG","Sign In With Credential:Success");
                            FirebaseUser firebaseUser =mauth.getCurrentUser();
                            Intent intent = new Intent(SignUp.this, UserMenu.class);
                            updateAccessFlagForShloka(1);
                            startActivity(intent);
                            finish();
                            //updateUI(user);

                        }
                        else {
                            //if signin fails ,displays a message to the user
                            Toast.makeText(SignUp.this, "There was an error in login", Toast.LENGTH_SHORT).show();
                            Log.w("TAG","Sign in With Credentials:Failure");
                            Toast.makeText(SignUp.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(SignUp.this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        mauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SignUp.this, UserMenu.class);
                                    startActivity(intent);
                                    updateAccessFlagForShloka(1);
                                    finish();
                                }
                            });

                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    protected void customizeGoogleButton(SignInButton signInButton) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);
            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(15);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setText("");
                tv.setBackgroundResource(R.mipmap.google);
                return;
            }
        }
    }
    private int updateAccessFlagForShloka(int id) {
        DataBaseHandlerShloka dataBaseHandlerShloka = new DataBaseHandlerShloka(this);
        int ret = dataBaseHandlerShloka.updateAccessFlag(new Shlokas(id,0,1));
      List<Shlokas> shlokases = dataBaseHandlerShloka.getAllShlokas();
        System.out.println("After Update");
        for (Shlokas up:shlokases) {
            String log = "ID:" + up.getId() + ",Verse:" + up.getVerse_details() + ",Translation:" + up.getVerse_translation() + ",Purpose:" + up.getVerse_purpose() + ",Chapter_id" + up.getChapter_id() +",Access Flag "+up.getAccessFlag();
            Log.d("Entry:",log);
        }
        if (ret == 1){
            editor = sharedPreferences2.edit();
            editor.putString("current_chapter","1");
            editor.putString("date",formattedDate);
            editor.apply();
        }
        else{
            Toast.makeText(SignUp.this,"UnSuccessfull",Toast.LENGTH_SHORT).show();
        }
        return ret;
    }
}
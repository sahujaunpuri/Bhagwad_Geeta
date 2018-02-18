package com.application.aayush.geeta;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    Toolbar toolbar;
    String name,mobile_number,email,address,city;
    TextView title,notify,last_modified_time;
    ImageButton navigate;
    SharedPreferences sharedPreferences;
    public static final String DEFAULT = "N/A";
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Regular.ttf");
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        title = (TextView)view.findViewById(R.id.toobar_title);
        notify = (Button)view.findViewById(R.id.notification_button1);
        last_modified_time = (TextView)view.findViewById(R.id.textView3);
        sharedPreferences = getActivity().getSharedPreferences("app_data", Context.MODE_PRIVATE);
        last_modified_time.setText(sharedPreferences.getString("last_modified_date",DEFAULT)+","+sharedPreferences.getString("last_modified_time",DEFAULT));
        title.setTypeface(customFont);
        navigate = (ImageButton) view.findViewById(R.id.button14);
        name = getArguments().getString("user_name");
        mobile_number = getArguments().getString("user_mobilenumber");
        email = getArguments().getString("user_email");
        address = getArguments().getString("user_address");
        city = getArguments().getString("user_city");
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserMenu.class);
                intent.putExtra("user_name",name);
                intent.putExtra("user_mobilenumber",mobile_number);
                intent.putExtra("user_email",email);
                intent.putExtra("user_address",address);
                intent.putExtra("user_city",city);
                startActivity(intent);
            }
        });

        return view;
    }

}

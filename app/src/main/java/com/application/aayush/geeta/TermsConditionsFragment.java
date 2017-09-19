package com.application.aayush.geeta;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsConditionsFragment extends Fragment {

    TextView text1,text2;
    Toolbar toolbar;
    Button back;
    String name,mobile_number,email,address,city;
    public TermsConditionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_terms_conditions, container, false);
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        back = (Button)view.findViewById(R.id.button41);
        name = getArguments().getString("user_name");
        mobile_number = getArguments().getString("user_mobilenumber");
        email = getArguments().getString("user_email");
        address = getArguments().getString("user_address");
        city = getArguments().getString("user_city");
        back.setOnClickListener(new View.OnClickListener() {
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

        text1 = (TextView)view.findViewById(R.id.textView22);
        text2= (TextView)view.findViewById(R.id.textView33);
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Regular.ttf");
        text1.setTypeface(customFont);
        text2.setTypeface(customFont);
        text1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        text1.setTextColor(getResources().getColor(R.color.colorblack));
        text2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        text2.setTextColor(getResources().getColor(R.color.colorblack));
        return view;
    }
}

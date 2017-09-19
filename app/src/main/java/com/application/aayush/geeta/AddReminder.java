package com.application.aayush.geeta;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddReminder extends Fragment {

 /*   RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
 */
    int hour,minute;
    Toolbar toolbar;
    Bundle bundle;
    TextView time;
    Switch aSwitch;
    CheckBox repeat,vibrate;
    Button sunday,monday,tuesday,wednesday,thursday,friday,saturday,alarm,delete,open_close;
    RelativeLayout.LayoutParams alarmImage,params,delete_params,openclose_params,param1;
    EditText label;
    TextInputLayout textInputLayout;
    String text;
    public AddReminder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    final View view =  inflater.inflate(R.layout.fragment_add_reminder, container, false);
        final View view =  inflater.inflate(R.layout.layout_add_reminder, container, false);
        bundle = getArguments();
        time = (TextView)view.findViewById(R.id.textView47);
        aSwitch = (Switch)view.findViewById(R.id.switch3);
        repeat = (CheckBox)view.findViewById(R.id.checkBox3);
        vibrate = (CheckBox)view.findViewById(R.id.checkBox4);
        sunday = (Button) view.findViewById(R.id.day1);
        monday = (Button)view.findViewById(R.id.day2);
        tuesday = (Button)view.findViewById(R.id.day3);
        wednesday = (Button)view.findViewById(R.id.day4);
        thursday = (Button)view.findViewById(R.id.day5);
        friday = (Button)view.findViewById(R.id.day6);
        saturday = (Button)view.findViewById(R.id.day7);
        alarm = (Button)view.findViewById(R.id.button17);
        textInputLayout = (TextInputLayout)view.findViewById(R.id.input_layout_label);
        label = (EditText)view.findViewById(R.id.input_label);
        delete = (Button)view.findViewById(R.id.button18);
        open_close = (Button)view.findViewById(R.id.button19);
        param1 = (RelativeLayout.LayoutParams)sunday.getLayoutParams();

        alarmImage = (RelativeLayout.LayoutParams)alarm.getLayoutParams();
        delete_params = (RelativeLayout.LayoutParams)delete.getLayoutParams();
        params = (RelativeLayout.LayoutParams)textInputLayout.getLayoutParams();
        openclose_params = (RelativeLayout.LayoutParams)open_close.getLayoutParams();
        repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (repeat.isChecked()){
                    alarmImage.setMargins(4,158,15,0);
                    params.setMargins(14,188,35,0);
                    delete_params.setMargins(4,267,15,10);
                    openclose_params.setMargins(0,272,15,10);
                    sunday.setVisibility(View.VISIBLE);
                    monday.setVisibility(View.VISIBLE);
                    tuesday.setVisibility(View.VISIBLE);
                    wednesday.setVisibility(View.VISIBLE);
                    thursday.setVisibility(View.VISIBLE);
                    friday.setVisibility(View.VISIBLE);
                    saturday.setVisibility(View.VISIBLE);
                    alarm.setLayoutParams(alarmImage);
                    textInputLayout.setLayoutParams(params);
                    delete.setLayoutParams(delete_params);
                    open_close.setLayoutParams(openclose_params);

                    Toast.makeText(AddReminder.this.getContext(),String.valueOf(param1.topMargin), Toast.LENGTH_SHORT).show();
                }
                else {
                    alarmImage.setMargins(4,109,15,0);
                    params.setMargins(14,139,35,0);
                    delete_params.setMargins(4,218,15,10);
                    openclose_params.setMargins(0,223,15,10);
                    sunday.setVisibility(View.GONE);
                    monday.setVisibility(View.GONE);
                    tuesday.setVisibility(View.GONE);
                    wednesday.setVisibility(View.GONE);
                    thursday.setVisibility(View.GONE);
                    friday.setVisibility(View.GONE);
                    saturday.setVisibility(View.GONE);
                    alarm.setLayoutParams(alarmImage);
                    textInputLayout.setLayoutParams(params);
                    delete.setLayoutParams(delete_params);
                    open_close.setLayoutParams(openclose_params);
                }
            }
        });
        /*
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(AddReminder.this.getContext());
*/
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
/*
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
*/
        if(bundle == null){
            Toast.makeText(AddReminder.this.getContext(),"Null object reference",Toast.LENGTH_SHORT).show();
        }
        else {
            hour = getArguments().getInt("hour");
            minute = getArguments().getInt("minute");
            if(hour >= 1 && hour <= 12){
                if(minute<10){
                    text = hour+":0"+minute+" a.m. ";
                }
                else {
                    text = hour + ":" + minute + " p.m. ";
                }
            }
            else{
                if(minute<10){
                    text= hour+":0"+minute+" p.m. ";
                }
                else {
                    text = hour + ":" + minute + " p.m. ";
                }
            }
            time.setText(text);
/*            Toast.makeText(AddReminder.this.getContext(),hour+":"+minute,Toast.LENGTH_SHORT).show();
*/

        }
  //      TimeAdapter ca = new TimeAdapter(createList(1));
//        recyclerView.setAdapter(ca);
        return view;
    }
/*
    private List<ReminderDetails> createList(int size) {
        String min = " ";
        List<ReminderDetails> result = new ArrayList<ReminderDetails>();
        for (int i=1; i <= size; i++) {
            ReminderDetails ci = new ReminderDetails();
            if(hour >= 1 && hour <= 12){
                if(minute<10){
                   ci.time = hour+":0"+minute+" a.m. ";
                }
                else {
                    ci.time = hour + ":" + minute + " p.m. ";
                }
            }
            else{
                if(minute<10){
                    ci.time = hour+":0"+minute+" p.m. ";
                }
                else {
                    ci.time = hour + ":" + minute + " p.m. ";
                }
            }
            result.add(ci);
        }
        return result;
    }
*/

}

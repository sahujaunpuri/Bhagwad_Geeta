package com.application.aayush.geeta;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    TextView title;
    Spinner languages,fonts;
    Switch notifications,readMode;
    Toolbar toolbar;
    Button notify;
    ImageButton navigate;
    String name,mobile_number,email,address,city;
    Bundle bundle ;
    SharedPreferences sharedPreferences;
    public static final String default_value = "12";
    String ret;
    String fonts_array[] = {"12","14","16","18","20"};
    String temp1 = "",temp = "";
    int n= fonts_array.length,pos = 0;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Regular.ttf");
        bundle = new Bundle();
        sharedPreferences = getActivity().getSharedPreferences("app_settings",Context.MODE_PRIVATE);
        ret = sharedPreferences.getString("font_size",default_value);
        if(!fonts_array[0].equals(ret)){
                temp = fonts_array[0];
                fonts_array[0] = ret;
        }
        for(int j = 1;j< n;j++) {
           if(ret.equals(fonts_array[j])){
               pos = j;
               fonts_array[pos] = temp;
           }
        }

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        title = (TextView)view.findViewById(R.id.toobar_title);
        notify = (Button)view.findViewById(R.id.notification_button1);
        title.setTypeface(customFont);
        navigate = (ImageButton) view.findViewById(R.id.button14);
        name = getArguments().getString("user_name");
        mobile_number = getArguments().getString("user_mobilenumber");
        email = getArguments().getString("user_email");
        address = getArguments().getString("user_address");
        city = getArguments().getString("user_city");
        bundle.putString("user_name",name);
        bundle.putString("user_mobilenumber",mobile_number);
        bundle.putString("user_email",email);
        bundle.putString("user_address",address);
        bundle.putString("user_city",city);
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
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                notificationFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.settings_layout,notificationFragment,notificationFragment.getTag()).commit();

            }
        });
        //creating adapter for spinner
        final ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.languages_supported,android.R.layout.simple_spinner_item);
        //final ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(getContext(),R.array.font_size,android.R.layout.simple_spinner_item);
        //languages = (Spinner)view.findViewById(R.id.spinner);
        fonts = (Spinner)view.findViewById(R.id.spinner2);
        notifications = (Switch) view.findViewById(R.id.Switch);
        readMode = (Switch)view.findViewById(R.id.switch1);
        //specify the layout when the dropdown appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
//        languages.setAdapter(arrayAdapter);
        //fonts.setAdapter(arrayAdapter1);
        setHasOptionsMenu(true);
  /*      languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(this,arrayAdapter.getItem(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
  */
        ArrayAdapter aa = new ArrayAdapter(SettingsFragment.this.getContext(),android.R.layout.simple_spinner_item,fonts_array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonts.setAdapter(aa);
        fonts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               View view1 = changeFontSize(parent,position,inflater,container);
                editor.putString("font_size",parent.getItemAtPosition(position).toString());
                editor.apply();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String font = sharedPreferences.getString("font_size",default_value);
                int length = getResources().getStringArray(R.array.font_size).length;
                String[] resultArr = getResources().getStringArray(R.array.font_size);
                String[] newArr = new String[length];
                //ArrayList<String> newArr = new ArrayList<String>();
                int position = 0;
                for (int i = 0 ;i<length;i++){
                    String val = resultArr[i];
                    if(val == font ){
                        newArr[0] = val;
                        position = i;
                    }
                }
                for (int i =0,j=1 ;i<length;i++){
                    if (i == position)
                        continue;
                    newArr[j] = resultArr[i];
                    j++;
                }
                for(String x: newArr){
                    System.out.print(x);
                }
                //Toast.makeText(getContext(),newArr[0],Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
                ArrayAdapter aa = new ArrayAdapter(SettingsFragment.this.getContext(),android.R.layout.simple_spinner_item,newArr);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fonts.setAdapter(aa);
            }
        });
        return view;
    }

    private View changeFontSize(AdapterView<?> parent, int position,LayoutInflater layoutInflater,ViewGroup container) {
        View view1 = layoutInflater.inflate(R.layout.activity_user_menu, container, false);
        TextView textView1 = (TextView)view1.findViewById(R.id.textView20);
        TextView textView2 = (TextView)view1.findViewById(R.id.textView41);
        TextView textView3 = (TextView)view1.findViewById(R.id.textView43);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP,parent.getItemIdAtPosition(position));
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,parent.getItemIdAtPosition(position));
        //textView3.setTextSize(parent.getItemIdAtPosition(position));
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        return view1;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
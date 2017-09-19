package com.application.aayush.geeta;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    TextView title;
    Spinner languages,fonts;
    Switch notifications,readMode;
    Toolbar toolbar;
    Button notify,navigate;
    String name,mobile_number,email,address,city;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Regular.ttf");
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        title = (TextView)view.findViewById(R.id.toobar_title);
        notify = (Button)view.findViewById(R.id.notification_button1);
        title.setTypeface(customFont);
        navigate = (Button)view.findViewById(R.id.button14);
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
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.settings_layout,notificationFragment,notificationFragment.getTag()).commit();

            }
        });
        //creating adapter for spinner
        final ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.languages_supported,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(getContext(),R.array.font_size,android.R.layout.simple_spinner_item);
        languages = (Spinner)view.findViewById(R.id.spinner);
        fonts = (Spinner)view.findViewById(R.id.spinner2);
        notifications = (Switch) view.findViewById(R.id.Switch);
        readMode = (Switch)view.findViewById(R.id.switch1);
        //specify the layout when the dropdown appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        languages.setAdapter(arrayAdapter);
        fonts.setAdapter(arrayAdapter1);
        setHasOptionsMenu(true);
        languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(this,arrayAdapter.getItem(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fonts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
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

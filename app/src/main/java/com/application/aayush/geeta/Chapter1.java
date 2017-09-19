package com.application.aayush.geeta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chapter1 extends Fragment {

    Toolbar toolbar;
    Button back;
    public Chapter1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter1, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        back = (Button)view.findViewById(R.id.notification_button1);
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen homeScreen = new Homescreen();
                FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.homescreen_layout,homeScreen,homeScreen.getTag()).commit();
            }
        });
        return view;
    }

}

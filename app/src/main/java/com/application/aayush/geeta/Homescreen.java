package com.application.aayush.geeta;


import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homescreen extends Fragment {
    Toolbar toolbar;
    Button notify,play_music,edit;
    DrawerLayout drawerLayout;
    TextView header,header2,header3,header4,textView1,textView2,textView3,learningText;
    ViewSwitcher viewSwitcher;
    EditText editText;
    public Homescreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_homescreen, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
/*
        final String text1 = getArguments().getString("name");
        final String text2 = getArguments().getString("mobile_number");
        final String text3 = getArguments().getString("email");
        final String text4 = getArguments().getString("address");
        final String text5 = getArguments().getString("city");

        Toast.makeText(Homescreen.this.getContext(),text1, Toast.LENGTH_SHORT).show();*/
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
        header = (TextView)view.findViewById(R.id.textView13);
        textView1 = (TextView)view.findViewById(R.id.textView20);
        header2 = (TextView)view.findViewById(R.id.textView40);
        header3 = (TextView)view.findViewById(R.id.textView42);
        header4 = (TextView)view.findViewById(R.id.textView44);
        textView2 = (TextView)view.findViewById(R.id.textView41);
        textView3 = (TextView)view.findViewById(R.id.textView43);
        learningText = (TextView)view.findViewById(R.id.learning_text);
        edit = (Button)view.findViewById(R.id.button15);
        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Regular.ttf");
        Typeface customFont1 = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Heavy.ttf");
        Typeface customFont2 = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lato-Italic.ttf");
        header.setTypeface(customFont1);
        header2.setTypeface(customFont1);
        header3.setTypeface(customFont1);
        header4.setTypeface(customFont);
        textView1.setTypeface(customFont);
        textView2.setTypeface(customFont);
        textView3.setTypeface(customFont);
        textView1.setText(" Yada yada hi dharmasya glanirbhavati bharata Abhythanamadharmasya tadatmanam srijamyaham");
        textView2.setText(" Whenever there is decay of righteousness, O Bharata,And there is exaltation of unrighteousness, then I Myself come forth");
        textView3.setText("Whenever virtue subsides and wickedness prevails, I manifest Myself. To establish virtue, to destroy evil, to save the good I come from Yuga (age) to Yuga.");

        notify = (Button)view.findViewById(R.id.notification_button1);
        play_music = (Button)view.findViewById(R.id.imageButton2);
        final MediaPlayer  mediaPlayer = MediaPlayer.create(Homescreen.this.getContext(),R.raw.yada_yada_hi);
        //drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher = (ViewSwitcher)view.findViewById(R.id.view_switcher);
                viewSwitcher.showNext();
                editText = (EditText)view.findViewById(R.id.hidden_edit_view);
                /*edit.setText();*/
            }
        });
       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


*//*
                Intent intent = new Intent(getActivity(),UserMenu.class);
                intent.putExtra("user_name",text1);
                intent.putExtra("user_mobilenumber",text2);
                intent.putExtra("user_email",text3);
                intent.putExtra("user_address",text4);
                intent.putExtra("user_city",text5);
                startActivity(intent);

*//*

            }
        });
*/        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.homescreen_layout,notificationFragment,notificationFragment.getTag()).commit();

            }
        });
        play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()==true){
                    mediaPlayer.stop();
                }
                else
                mediaPlayer.start();
                /*Uri uri = Uri.parse("android.resource://com.application.aayush.geeta/raw/yada_yad_hi");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);*/
            }
        });
        return view;
    }


}

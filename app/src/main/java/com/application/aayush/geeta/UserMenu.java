package com.application.aayush.geeta;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.text.StringCharacterIterator;
import java.util.List;

import static java.lang.System.out;

public class UserMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView chapter,header,header2,header3,header4,textView1,textView2,textView3,learningText,name,contact_no;
    Button edit;
    ViewSwitcher viewSwitcher;
    String uname,umobilenumber,uemail,uaddress,ucity;
    EditText editText;
    Button play_music,save;
    ImageButton rate1,rate2,rate3,rate4,rate5,rate6,rate7,rate8,rate9,rate10;
    Bundle bundle,bundle1;
    String verse,translation,purport,title,diary_entry;

    ImageView back,front;
    int id = 0,count = 0,chapter_no = 0,chapter_id = 0;
    DiaryDatabaseHandler diaryDatabaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        final DataBaseHandlerShloka db = new DataBaseHandlerShloka(this);
        diaryDatabaseHandler = new DiaryDatabaseHandler(this);
        Drawable drawable= getResources().getDrawable(R.mipmap.menu1);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable new_drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 54, 54, true));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(new_drawable);
        back = (ImageView)findViewById(R.id.imageView18);
        front = (ImageView)findViewById(R.id.imageView21);
        chapter = (TextView)findViewById(R.id.textView14);
        header = (TextView)findViewById(R.id.textView13);
        textView1 = (TextView)findViewById(R.id.textView20);
        header2 = (TextView)findViewById(R.id.textView40);
        header3 = (TextView)findViewById(R.id.textView42);
        header4 = (TextView)findViewById(R.id.textView44);
        textView2 = (TextView)findViewById(R.id.textView41);
        textView3 = (TextView)findViewById(R.id.textView43);
        learningText = (TextView)findViewById(R.id.learning_text);
        editText = (EditText)findViewById(R.id.hidden_edit_view);
        edit = (Button) findViewById(R.id.editbutton);
        save = (Button)findViewById(R.id.button20);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");
        Typeface customFont1 = Typeface.createFromAsset(getAssets(),"fonts/Lato-Heavy.ttf");
        Typeface customFont2 = Typeface.createFromAsset(getAssets(),"fonts/Lato-Italic.ttf");
        header.setTypeface(customFont1);
        header2.setTypeface(customFont1);
        header3.setTypeface(customFont1);
        header4.setTypeface(customFont);
        textView1.setTypeface(customFont);
        textView2.setTypeface(customFont);
        textView3.setTypeface(customFont);
        learningText.setTypeface(customFont2);
        count = db.userCount();
        Shlokas shlokas = db.getsholka(1);
        id = shlokas.getId();
        title = "Chapter "+String.valueOf(id);
        verse = shlokas.getVerse_details();
        translation = shlokas.getVerse_translation();
        purport = shlokas .getVerse_purpose();
        chapter_id = shlokas.getChapter_id();
        chapter.setText(String.valueOf(id));
        textView1.setText(verse);
        textView2.setText(translation);
        textView3.setText(purport);
        Toast.makeText(UserMenu.this,"Chapter_ID "+String.valueOf(chapter_id),Toast.LENGTH_SHORT).show();
        List<Shlokas> shlokases = db.getAllShlokas();
        for (Shlokas up:shlokases) {
            String log = "ID:" + up.getId() + ",Verse:" + up.getVerse_details() + ",Translation:" + up.getVerse_translation() + ",Purpose:" + up.getVerse_purpose() + ",Chapter_id" + up.getChapter_id();
            //writing users to the list
            Log.d("Entry:",log);
        }

        int r = db.getChapterId(Integer.parseInt(chapter.getText().toString()));

        if(r == 0){
            save.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data_to_be_saved = editText.getText().toString();
                    Toast.makeText(UserMenu.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                    saveData(data_to_be_saved,chapter);
                    learningText = (TextView)findViewById(R.id.learning_text);
                    learningText.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.GONE);
                    learningText.setText(data_to_be_saved);

                }
            });
        }
        else if(r == 1){
            Toast.makeText(UserMenu.this,"After Refresh",Toast.LENGTH_SHORT).show();

            save.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            Diary diary = diaryDatabaseHandler.getContent(Integer.parseInt(chapter.getText().toString()));
            final String data = diary.getDiary_details();
            learningText.setVisibility(View.VISIBLE);
            editText.setVisibility(View.GONE);
            learningText.setText(data);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UserMenu.this,data,Toast.LENGTH_SHORT).show();

                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chapter_no = Integer.parseInt(chapter.getText().toString());

                 if(chapter_no == 1){
                    chapter_no = db.userCount();
                    Shlokas shlokas = db.getsholka(chapter_no);
                    id = shlokas.getId();
                    verse = shlokas.getVerse_details();
                    translation = shlokas.getVerse_translation();
                    purport = shlokas .getVerse_purpose();
                    chapter_id = shlokas.getChapter_id();
/*
                    Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                    if(diary == null){}
                    else {
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }
*/
                    if (chapter_id == 0){
                        //learningText.setText("What do you learn from today's Chapter...");
                        learningText.setText("Chapter 1");
                        save.setVisibility(View.VISIBLE);
                        edit.setVisibility(View.GONE);
                        save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String data_to_be_saved = editText.getText().toString();
                                Toast.makeText(UserMenu.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                                saveData(data_to_be_saved,chapter);
                                learningText = (TextView)findViewById(R.id.learning_text);
                                learningText.setVisibility(View.VISIBLE);
                                editText.setVisibility(View.GONE);
                                learningText.setText(data_to_be_saved);

                            }
                        });

                    }
                    else{
                        Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }
                    chapter.setText(String.valueOf(chapter_no));
                    textView1.setText(verse);
                    textView2.setText(translation);
                    textView3.setText(purport);

               }
                else {
                    chapter_no = chapter_no - 1;
                    Shlokas shlokas = db.getsholka(chapter_no);
/*
                    Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                    if(diary == null){}
                    else {
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }
*/
                    if (chapter_id == 0){
//                        learningText.setText("What do you learn from today's Chapter...");
                        learningText.setText("Chapter 1");
                        save.setVisibility(View.VISIBLE);
                        edit.setVisibility(View.GONE);
                        save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String data_to_be_saved = editText.getText().toString();
                                Toast.makeText(UserMenu.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                                saveData(data_to_be_saved,chapter);
                                learningText = (TextView)findViewById(R.id.learning_text);
                                learningText.setVisibility(View.VISIBLE);
                                editText.setVisibility(View.GONE);
                                learningText.setText(data_to_be_saved);

                            }
                        });

                    }
                    else{
                        Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }

                    id = shlokas.getId();
                    verse = shlokas.getVerse_details();
                    translation = shlokas.getVerse_translation();
                    purport = shlokas.getVerse_purpose();
                    chapter_id = shlokas.getChapter_id();
                    out.println(chapter_id);
                    chapter.setText(String.valueOf(chapter_no));
                    textView1.setText(verse);
                    textView2.setText(translation);
                    textView3.setText(purport);
                    }
            }
        });
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chapter_no = Integer.parseInt(chapter.getText().toString());
                 if (chapter_no == db.userCount()){
                    chapter_no = 1;
                    Shlokas shlokas = db.getsholka(chapter_no);
/*
                    Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                    if(diary == null){}
                    else {
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }
*/
                    if (chapter_id == 0){
                     //   learningText.setText("What do you learn from today's Chapter...");
                        learningText.setText("Chapter 1");
                        save.setVisibility(View.VISIBLE);
                        edit.setVisibility(View.GONE);
                        save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String data_to_be_saved = editText.getText().toString();
                                Toast.makeText(UserMenu.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                                saveData(data_to_be_saved,chapter);
                                learningText = (TextView)findViewById(R.id.learning_text);
                                learningText.setVisibility(View.VISIBLE);
                                editText.setVisibility(View.GONE);
                                learningText.setText(data_to_be_saved);

                            }
                        });

                    }
                    else{
                        Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);
                        save.setVisibility(View.GONE);
                        edit.setVisibility(View.VISIBLE);
                        Toast.makeText(UserMenu.this,"Inside else of Front button",Toast.LENGTH_SHORT).show();
                    }
                    id = shlokas.getId();
                    verse = shlokas.getVerse_details();
                    translation = shlokas.getVerse_translation();
                    purport = shlokas .getVerse_purpose();
                    chapter_id = shlokas.getChapter_id();
                    out.println(chapter_id);
                    chapter.setText(String.valueOf(chapter_no));
                    textView1.setText(verse);
                    textView2.setText(translation);
                    textView3.setText(purport);


                }
                else {
                    chapter_no = chapter_no + 1;
                    Shlokas shlokas = db.getsholka(chapter_no);
/*
                    Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                    if(diary == null){}
                    else {
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }
*/
                    if (chapter_id == 0){
                     //   learningText.setText("What do you learn from today's Chapter...");
                        learningText.setText("Chapter 1");
                    }
                    else{
                        Diary diary = diaryDatabaseHandler.getContent(chapter_no);
                        diary_entry = diary.getDiary_details();
                        learningText.setText(diary_entry);

                    }

                    id = shlokas.getId();
                    verse = shlokas.getVerse_details();
                    translation = shlokas.getVerse_translation();
                    purport = shlokas.getVerse_purpose();
                    chapter_id = shlokas.getChapter_id();
                    out.println(chapter_id);
                    chapter.setText(String.valueOf(chapter_no));
                    textView1.setText(verse);
                    textView2.setText(translation);
                    textView3.setText(purport);
                    }
            }
        });
        int r1 = db.getChapterId(Integer.parseInt(chapter.getText().toString()));
        Toast.makeText(UserMenu.this.getApplicationContext(),String.valueOf(r1),Toast.LENGTH_SHORT).show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a frame layout
                //FrameLayout frameLayout = new FrameLayout(getApplicationContext());
                RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
                // set the layout params to fill the activity
                relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                // set an id to the layout
                relativeLayout.setId(R.id.fragment_chapter);
                // set the layout as Activity content
                setContentView(relativeLayout);
                // Finally , add the fragment
                Chapter chapter = new Chapter();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.fragment_chapter,chapter).commit();
            }
        });
        play_music = (Button)findViewById(R.id.imageButton2);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.yada_yada_hi);
        //drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        viewSwitcher = (ViewSwitcher)findViewById(R.id.view_switcher);
        bundle = getIntent().getExtras();
        uname = bundle.getString("user_name");
        umobilenumber = bundle.getString("user_mobilenumber");
        uemail = bundle.getString("user_email");
        uaddress = bundle.getString("user_address");
        ucity = bundle.getString("user_city");
        learningText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                learningText.setVisibility(View.GONE);

            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserMenu.this,"Editext",Toast.LENGTH_SHORT).show();
                learningText.setVisibility(View.VISIBLE);
                learningText.setText(editText.getText().toString());
                editText.setVisibility(View.GONE);
            }
        });

        play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                else
                    mediaPlayer.start();
                /*Uri uri = Uri.parse("android.resource://com.application.aayush.geeta/raw/yada_yad_hi");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);*/
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("EveryDay Geeta");
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(new_drawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        name = (TextView)headerView.findViewById(R.id.user_name1);
        contact_no = (TextView)headerView.findViewById(R.id.mobile_no);
        name.setText(uname);
        contact_no.setText(umobilenumber);

    }
    private void saveData(String data_to_be_saved, TextView chapter) {
        DiaryDatabaseHandler diaryDatabaseHandler = new DiaryDatabaseHandler(this);
        DataBaseHandlerShloka dataBaseHandlerShloka = new DataBaseHandlerShloka(this);
        int chapter_no = Integer.parseInt(chapter.getText().toString());
        Toast.makeText(getApplicationContext(),data_to_be_saved + " "+Integer.toString(chapter_no),Toast.LENGTH_SHORT).show();
        diaryDatabaseHandler.addContent(new Diary(chapter_no,data_to_be_saved));
        dataBaseHandlerShloka.addChapter(new Shlokas(chapter_no,chapter_no));
        int v = dataBaseHandlerShloka.addChapter(new Shlokas(chapter_no,chapter_no));

        out.print("**************"+v);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_diary) {
            diaryFragment diaryFragment1 = new diaryFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            diaryFragment1.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_user_menu,diaryFragment1,diaryFragment1.getTag()).commit();

        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            settingsFragment.setArguments(bundle);
            fragmentManager2.beginTransaction().replace(R.id.content_user_menu,settingsFragment,settingsFragment.getTag()).commit();
        } else if (id == R.id.nav_userguide) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserMenu.this);
            View dialogLayout = getLayoutInflater().inflate(R.layout.activity_rate_us1,null);
            Button button1 = (Button)dialogLayout.findViewById(R.id.button);
            Button button2 = (Button)dialogLayout.findViewById(R.id.button3);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "May be later", Toast.LENGTH_SHORT).show();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(UserMenu.this);
                    View dialogLayout1 = getLayoutInflater().inflate(R.layout.activity_rating,null);
                    rate1 = (ImageButton)dialogLayout1.findViewById(R.id.rating1);
                    rate6 = (ImageButton)dialogLayout1.findViewById(R.id.rating6);
                    rate1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate1.setVisibility(View.INVISIBLE);
                            rate6.setVisibility(View.VISIBLE);
                        }
                    });
                    rate6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate6.setVisibility(View.INVISIBLE);
                            rate1.setVisibility(View.VISIBLE);

                        }
                    });
                    rate2 = (ImageButton)dialogLayout1.findViewById(R.id.rating2);
                    rate7 = (ImageButton)dialogLayout1.findViewById(R.id.rating7);
                    rate2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate2.setVisibility(View.INVISIBLE);
                            rate7.setVisibility(View.VISIBLE);

                        }
                    });
                    rate7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate7.setVisibility(View.INVISIBLE);
                            rate2.setVisibility(View.VISIBLE);

                        }
                    });
                    rate3 = (ImageButton)dialogLayout1.findViewById(R.id.rating3);
                    rate4 = (ImageButton)dialogLayout1.findViewById(R.id.rating4);
                    rate5 = (ImageButton)dialogLayout1.findViewById(R.id.rating5);
                    rate8 = (ImageButton)dialogLayout1.findViewById(R.id.rating8);
                    rate9 = (ImageButton)dialogLayout1.findViewById(R.id.rating9);
                    rate10 = (ImageButton)dialogLayout1.findViewById(R.id.rating10);
                    rate3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate3.setVisibility(View.INVISIBLE);
                            rate8.setVisibility(View.VISIBLE);

                        }
                    });
                    rate8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate8.setVisibility(View.INVISIBLE);
                            rate3.setVisibility(View.VISIBLE);

                        }
                    });
                    rate4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate4.setVisibility(View.INVISIBLE);
                            rate9.setVisibility(View.VISIBLE);

                        }
                    });
                    rate9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate9.setVisibility(View.INVISIBLE);
                            rate4.setVisibility(View.VISIBLE);

                        }
                    });
                    rate5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate5.setVisibility(View.INVISIBLE);
                            rate10.setVisibility(View.VISIBLE);

                        }
                    });
                    rate10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rate10.setVisibility(View.INVISIBLE);
                            rate5.setVisibility(View.VISIBLE);

                        }
                    });

                    /*  RatingBar ratingBar = (RatingBar)dialogLayout1.findViewById(R.id.ratingBar);
                    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(UserMenu.this);
                            View dialogLayout2 = getLayoutInflater().inflate(R.layout.activity_rating1,null);
                            alertDialogBuilder2.setView(dialogLayout2);
                            AlertDialog alertDialog2 = alertDialogBuilder2.create();
                            alertDialog2.show();

                        }
                    });
 */                   alertDialogBuilder1.setView(dialogLayout1);
                    AlertDialog alertDialog1 = alertDialogBuilder1.create();
                    alertDialog1.show();
                }
            });
            alertDialogBuilder.setView(dialogLayout);
            AlertDialog alertDialog1 = alertDialogBuilder.create();
            alertDialog1.show();


        } else if (id == R.id.nav_terms) {
            TermsConditionsFragment termsConditionsFragment = new TermsConditionsFragment();
            termsConditionsFragment.setArguments(bundle);
            FragmentManager fragmentManager1 = getSupportFragmentManager();
            fragmentManager1.beginTransaction().replace(R.id.content_user_menu,termsConditionsFragment,termsConditionsFragment.getTag()).commit();

        }
        else if (id == R.id.nav_reminder) {
            Intent intent = new Intent(this,SetReminder.class);
            intent.putExtra("user_name",uname);
            intent.putExtra("user_mobilenumber",umobilenumber);
            intent.putExtra("user_email",uemail);
            intent.putExtra("user_address",uaddress);
            intent.putExtra("user_city",ucity);
         //   Toast.makeText(getApplicationContext(),uname+" "+umobilenumber+" ",Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
        else if (id == R.id.nav_log_out) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
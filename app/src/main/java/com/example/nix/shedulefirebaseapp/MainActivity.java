package com.example.nix.shedulefirebaseapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private FragmentManager fm;
    private Fragment fragment;
    private Date mDate;
    private Calendar mCalendar;
    private int day; // переменная для передачи данных о дне от фрагмента к фрагменту
    private int week_of_year;
    public final static String DATA = "DAY_OF_WEEK";
    public final static String WEEK = "WEEK_OF_YEAR";
    private Bundle mBundle;
    private Bundle mBundleWeek;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    private Long week_number; //переменная для счёта недели
    private int VISIBILITY = 0;
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBundle = new Bundle();
        mBundleWeek = new Bundle();
        mDate = new Date();
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        week_of_year = mCalendar.get(Calendar.WEEK_OF_YEAR);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.containerView);
        fragment = new NoInfoFragment();
        fm.beginTransaction()
                .add(R.id.containerView,fragment)
                .commit();

        mToolbar.setTitle("Сегодня");
        if (isNetworkAvailable()){
            mDatabaseReference.child("weeknumber").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    week_number = (long)week_of_year - (Long)dataSnapshot.getValue() + 1l;
                    mBundleWeek.putLong(WEEK, week_number);
                    mToolbar.setSubtitle(week_number.toString() + " неделя");
                    fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .remove(fragment)
                            .commit();
                    fragment = new TodayFragment();
                    fragment.setArguments(mBundleWeek);
                    fm.beginTransaction()
                            .add(R.id.containerView,fragment)
                            .commit();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }else{
            mToolbar.setSubtitle("Ошибка!");
            fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .remove(fragment)
                    .commit();
            fragment = fm.findFragmentById(R.id.containerView);
            fragment = new NoNetworkFragment();
            fm.beginTransaction()
                    .add(R.id.containerView,fragment)
                    .commit();
        }

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.md:
                        mToolbar.setTitle("Понедельник");
                        if(isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            day = 0;
                            mBundle.putInt(DATA, day);
                            fragment = new SubjectsOfDayFragment();
                            fragment.setArguments(mBundle);
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else{
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.tud:
                        mToolbar.setTitle("Вторник");
                        if(isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            day = 1;
                            mBundle.putInt(DATA, day);
                            fragment = new SubjectsOfDayFragment();
                            fragment.setArguments(mBundle);
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.wd:
                        mToolbar.setTitle("Среда");
                        if(isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            day = 2;
                            mBundle.putInt(DATA, day);
                            fragment = new SubjectsOfDayFragment();
                            fragment.setArguments(mBundle);
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.thd:
                        mToolbar.setTitle("Четверг");
                        if(isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            day = 3;
                            mBundle.putInt(DATA, day);
                            fragment = new SubjectsOfDayFragment();
                            fragment.setArguments(mBundle);
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.fd:
                        mToolbar.setTitle("Пятница");
                        if (isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            day = 4;
                            mBundle.putInt(DATA, day);
                            fragment = new SubjectsOfDayFragment();
                            fragment.setArguments(mBundle);
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.sd:
                        mToolbar.setTitle("Суббота");
                        if (isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            day = 5;
                            mBundle.putInt(DATA, day);
                            fragment = new SubjectsOfDayFragment();
                            fragment.setArguments(mBundle);
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.hw:
                        mToolbar.setTitle("Домашнее задание");
                        if (isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new HomeWorkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.teach:
                        mToolbar.setTitle("Преподаватели");
                        if (isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new TeachersFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.ex:
                        mToolbar.setTitle("Экзамены");
                        if (isNetworkAvailable()){
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new ExamsFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }else {
                            fm.beginTransaction()
                                    .remove(fragment)
                                    .commit();
                            fragment = new NoNetworkFragment();
                            fm.beginTransaction()
                                    .add(R.id.containerView, fragment)
                                    .commit();
                        }
                        VISIBILITY = 1;
                        invalidateOptionsMenu();
                        return true;
                    case R.id.about_button:
                        Intent intent = new Intent(getApplication(), ActivityAbout.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem toggleItem = menu.findItem(R.id.home_button);
        if (VISIBILITY == 0){
            toggleItem.setVisible(false);
        }else{
            toggleItem.setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_button:
                mToolbar.setTitle("Сегодня");
                VISIBILITY = 0;
                if(isNetworkAvailable()){
                    fm.beginTransaction()
                            .remove(fragment)
                            .commit();
                    fragment = new TodayFragment();
                    fragment.setArguments(mBundleWeek);
                    fm.beginTransaction()
                            .add(R.id.containerView, fragment)
                            .commit();
                }else{
                    fm.beginTransaction()
                            .remove(fragment)
                            .commit();
                    fragment = new NoNetworkFragment();
                    fm.beginTransaction()
                            .add(R.id.containerView, fragment)
                            .commit();
                }
                mDrawerLayout.closeDrawers();
                invalidateOptionsMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

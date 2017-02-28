package com.example.nix.shedulefirebaseapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
    private Toolbar mToolbar;
    private FragmentManager fm;
    private Fragment fragment;
    private int week_of_year;
    public final static String DATA = "DAY_OF_WEEK";
    public final static String WEEK = "WEEK_OF_YEAR";
    private Bundle mBundle;
    private Bundle mBundleWeek;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    private Long week_number; //переменная для счёта недели
    private int VISIBILITY = 0; //переменная-триггер видимости кнопки "Домой"
    //коды дней
    private final int MONDAY = 0;
    private final int TUESDAY = 1;
    private final int WEDNESDAY = 2;
    private final int THURSDAY = 3;
    private final int FRIDAY = 4;
    private final int SATURDAY = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mBundle = new Bundle();
        mBundleWeek = new Bundle();
        Date mDate = new Date();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        week_of_year = mCalendar.get(Calendar.WEEK_OF_YEAR);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.containerView);
        fragment = new NoInfoFragment();
        fm.beginTransaction()
                .add(R.id.containerView,fragment)
                .commitAllowingStateLoss();
        setTodayFragment();
        mDatabaseReference.keepSynced(true);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        NavigationView mNavigationView = (NavigationView)findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.md:
                        loadSubjectsOfDayFragment("Понедельник", MONDAY);
                        return true;
                    case R.id.tud:
                        loadSubjectsOfDayFragment("Вторник", TUESDAY);
                        return true;
                    case R.id.wd:
                        loadSubjectsOfDayFragment("Среда", WEDNESDAY);
                        return true;
                    case R.id.thd:
                        loadSubjectsOfDayFragment("Четверг", THURSDAY);
                        return true;
                    case R.id.fd:
                        loadSubjectsOfDayFragment("Пятница", FRIDAY);
                        return true;
                    case R.id.sd:
                        loadSubjectsOfDayFragment("Суббота", SATURDAY);
                        return true;
                    case R.id.hw:
                        loadAnotherFragments("Домашнее задание");
                        return true;
                    case R.id.teach:
                        loadAnotherFragments("Преподаватели");
                        return true;
                    case R.id.ex:
                        loadAnotherFragments("Экзамены");
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
                setTodayFragment();
                mDrawerLayout.closeDrawers();
                invalidateOptionsMenu();
                VISIBILITY = 0;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadSubjectsOfDayFragment(String toolbarTitle, int code_of_day){
        mToolbar.setTitle(toolbarTitle);
            mBundle.putInt(DATA, code_of_day);
            fragment = new SubjectsOfDayFragment();
            fragment.setArguments(mBundle);
            fm.beginTransaction()
                    .replace(R.id.containerView, fragment)
                    .commitAllowingStateLoss();
        VISIBILITY = 1;
        invalidateOptionsMenu();
    }
    private void loadAnotherFragments(String title){
        mToolbar.setTitle(title);
        /*if (isNetworkAvailable()){*/
            switch(title){
                case "Домашнее задание":
                    fragment = new HomeWorkFragment();
                    break;
                case "Преподаватели":
                    fragment = new TeachersFragment();
                    break;
                case "Экзамены":
                    fragment = new ExamsFragment();
                    break;
                default:
                    fragment = new HomeWorkFragment();
                    break;
            }
            fm.beginTransaction()
                    .replace(R.id.containerView, fragment)
                    .commitAllowingStateLoss();
        VISIBILITY = 1;
        invalidateOptionsMenu();
    }
    private void setTodayFragment(){
        mToolbar.setTitle("Сегодня");
        mDatabaseReference.child("weeknumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                week_number = (long)week_of_year - (Long)dataSnapshot.getValue() + 1L;
                mBundleWeek.putLong(WEEK, week_number);
                mToolbar.setSubtitle(week_number.toString() + " неделя");
                fm = getSupportFragmentManager();
                fragment = new TodayFragment();
                fragment.setArguments(mBundleWeek);
                fm.beginTransaction()
                        .replace(R.id.containerView,fragment)
                        .commitAllowingStateLoss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //do nothing
            }
        });
    }
}

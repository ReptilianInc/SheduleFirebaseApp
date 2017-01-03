package com.example.nix.shedulefirebaseapp;

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


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private FragmentManager fm;
    private Fragment fragment;
    private int day; // перменная для передачи данных от фрагмента к фрагменту
    public final static String DATA = "DAY_OF_WEEK";
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.containerView);
        fragment = new TodayFragment();
        fm.beginTransaction()
                .add(R.id.containerView,fragment)
                .commit();
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mBundle = new Bundle();
        mToolbar.setTitle("Сегодня");
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.md:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Понедельник");
                        day = 0;
                        mBundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(mBundle);
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
                        return true;
                    case R.id.tud:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Вторник");
                        day = 1;
                        mBundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(mBundle);
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
                        return true;
                    case R.id.wd:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Среда");
                        day = 2;
                        mBundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(mBundle);
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
                        return true;
                    case R.id.thd:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Четверг");
                        day = 3;
                        mBundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(mBundle);
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
                        return true;
                    case R.id.fd:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Пятница");
                        day = 4;
                        mBundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(mBundle);
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
                        return true;
                    case R.id.sd:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Суббота");
                        day = 5;
                        mBundle.putInt(DATA, day);
                        fragment = new SubjectsOfDayFragment();
                        fragment.setArguments(mBundle);
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
                        return true;
                    case R.id.hw:
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                        mToolbar.setTitle("Домашняя работа");
                        fragment = new HomeWorkFragment();
                        fm.beginTransaction()
                                .add(R.id.containerView, fragment)
                                .commit();
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_button:
                fm.beginTransaction()
                        .remove(fragment)
                        .commit();
                mToolbar.setTitle("Сегодня");
                fragment = new TodayFragment();
                fm.beginTransaction()
                        .add(R.id.containerView, fragment)
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

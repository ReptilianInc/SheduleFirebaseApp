package com.example.nix.shedulefirebaseapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<Subject> mSubjects = new ArrayList<>();
    private SubjectAdapter mAdapter;
    //private FragmentManager mFragmentManager;
    //private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mAdapter = new SubjectAdapter(mSubjects);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
        prepareData();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                // пока пусто
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
    private void prepareData(){
        Subject subject1 = new Subject("Моделирование систем массового обслуживания", "08:30-10:00", "301A", "Ильин В.А.", 2, 1);
        Subject subject2 = new Subject("Правоведение", "10:10-11:40", "501A", "Иванова В.А.", 2, 1);
        Subject subject3 = new Subject("Базы данных", "11:50-13:20", "301A", "Ололоев В.А.", 2, 1);
        Subject subject4 = new Subject("Базы данных", "13:50-15:20", "301A", "Ололоев В.А.", 2, 1);
        mSubjects.add(subject1);
        mSubjects.add(subject2);
        mSubjects.add(subject3);
        mSubjects.add(subject4);
        mAdapter.notifyDataSetChanged();
    }
}

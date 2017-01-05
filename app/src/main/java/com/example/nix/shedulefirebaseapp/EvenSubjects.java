package com.example.nix.shedulefirebaseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nix on 03.01.2017.
 */

public class EvenSubjects extends AbstractSubjects {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            i = bundle.getInt(SubjectsOfDayFragment.DAY, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        ProgressBar mProgressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        RecyclerView mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        initializeUI(mRecyclerView, mProgressBar);
        return v;
    }
    @Override
    protected void checkRoot(int code){
        switch (code){
            case 0:
                ROOT = "mondayeven";
                break;
            case 1:
                ROOT = "tuesdayeven";
                break;
            case 2:
                ROOT = "wednesdayeven";
                break;
            case 3:
                ROOT = "thursdayeven";
                break;
            case 4:
                ROOT = "fridayeven";
                break;
            case 5:
                ROOT = "saturdayeven";
                break;
            default:
                ROOT = "mondayeven";
                break;
        }
    }
}

package com.example.nix.shedulefirebaseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by Nix on 03.01.2017.
 */

public class OddSubjects extends AbstractSubjects{
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
                ROOT = "mondayodd";
                break;
            case 1:
                ROOT = "tuesdayodd";
                break;
            case 2:
                ROOT = "wednesdayodd";
                break;
            case 3:
                ROOT = "thursdayodd";
                break;
            case 4:
                ROOT = "fridayodd";
                break;
            case 5:
                ROOT = "saturdayodd";
                break;
            default:
                ROOT = "mondayodd";
                break;
        }
    }
}

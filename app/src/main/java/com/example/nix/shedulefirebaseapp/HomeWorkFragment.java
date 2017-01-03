package com.example.nix.shedulefirebaseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class HomeWorkFragment extends Fragment {
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<HomeworkItem, HomeWorkAdapter.MyViewHolderHomeWork> mAdapter;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager sglm;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);

        mProgressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        sglm = new StaggeredGridLayoutManager(2,1);
        mRecyclerView.setLayoutManager(sglm);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAdapter = new FirebaseRecyclerAdapter<HomeworkItem, HomeWorkAdapter.MyViewHolderHomeWork>(
                HomeworkItem.class,
                R.layout.homework_card,
                HomeWorkAdapter.MyViewHolderHomeWork.class,
                mDatabaseReference.child("homeworktable")
        ) {
            @Override
            protected void populateViewHolder(HomeWorkAdapter.MyViewHolderHomeWork viewHolder, HomeworkItem model, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.descr.setText(model.getDescription());
            }
        };

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }
        });
        mRecyclerView.setLayoutManager(sglm);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }
}

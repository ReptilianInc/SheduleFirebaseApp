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

public class EvenSubjects extends Fragment {
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Subject, SubjectAdapter.MyViewHolder> mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager llm;
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
        llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAdapter = new FirebaseRecyclerAdapter<Subject, SubjectAdapter.MyViewHolder>(
                Subject.class,
                R.layout.subject_card,
                SubjectAdapter.MyViewHolder.class,
                mDatabaseReference.child("mondayeven")
        ) {
            @Override
            protected void populateViewHolder(SubjectAdapter.MyViewHolder viewHolder, Subject model, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.title.setText(model.getTitle());
                viewHolder.room.setText(model.getRoom());
                viewHolder.time.setText(model.getTime());
                viewHolder.teacher.setText(model.getTeacher());
            }
        };

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }
        });
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }
}

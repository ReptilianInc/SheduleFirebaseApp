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

public class ExamsFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        final ProgressBar mProgressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        RecyclerView mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.colorGray));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseRecyclerAdapter<Subject, SubjectAdapter.MyViewHolder> mAdapter = new FirebaseRecyclerAdapter<Subject, SubjectAdapter.MyViewHolder>(
                Subject.class,
                R.layout.subject_card,
                SubjectAdapter.MyViewHolder.class,
                mDatabaseReference.child("examstable")
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

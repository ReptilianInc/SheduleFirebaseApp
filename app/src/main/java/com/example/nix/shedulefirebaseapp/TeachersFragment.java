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

public class TeachersFragment extends Fragment {
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
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseRecyclerAdapter<TeacherItem, TeacherAdapter.MyViewHolderTeacher> mAdapter = new FirebaseRecyclerAdapter<TeacherItem, TeacherAdapter.MyViewHolderTeacher>(
                TeacherItem.class,
                R.layout.teacher_card,
                TeacherAdapter.MyViewHolderTeacher.class,
                mDatabaseReference.child("teachertable")
        ) {
            @Override
            protected void populateViewHolder(TeacherAdapter.MyViewHolderTeacher viewHolder, TeacherItem model, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.name.setText(model.getName());
                viewHolder.about.setText(model.getAbout());
            }
        };
        mDatabaseReference.keepSynced(true);
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

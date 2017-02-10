package com.example.nix.shedulefirebaseapp;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nix on 05.01.2017.
 */

/**
 * класс родитель для OddSubjects и EvenSubjects. Содержит в себе повторяющиеся части кода
**/

public abstract class AbstractSubjects extends Fragment {
    protected abstract void checkRoot(int code);
    protected String ROOT = "";
    protected int i;
    protected void initializeUI(RecyclerView recyclerView, final ProgressBar pb){
        recyclerView.setBackgroundColor(getResources().getColor(R.color.colorGray));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        checkRoot(i);
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseRecyclerAdapter<Subject, SubjectAdapter.MyViewHolder> mAdapter = new FirebaseRecyclerAdapter<Subject, SubjectAdapter.MyViewHolder>(
                Subject.class,
                R.layout.subject_card,
                SubjectAdapter.MyViewHolder.class,
                mDatabaseReference.child(ROOT)
        ) {
            @Override
            protected void populateViewHolder(SubjectAdapter.MyViewHolder viewHolder, Subject model, int position) {
                pb.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.title.setText(model.getTitle());
                viewHolder.room.setText(model.getRoom());
                viewHolder.time.setText(model.getTime());
                viewHolder.teacher.setText(model.getTeacher());
            }
        };
        mDatabaseReference.keepSynced(true);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }
        });
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
    }
}

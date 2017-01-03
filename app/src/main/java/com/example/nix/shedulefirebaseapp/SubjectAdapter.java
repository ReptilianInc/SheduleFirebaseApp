package com.example.nix.shedulefirebaseapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nix on 02.01.2017.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>{

    private List<Subject> subjectsList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, teacher, room, time;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.subject_view);
            teacher = (TextView) view.findViewById(R.id.teacher_view);
            room = (TextView) view.findViewById(R.id.room_view);
            time = (TextView) view.findViewById(R.id.time_view);
        }
    }
    public SubjectAdapter(List<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_card, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subject subject = subjectsList.get(position);
        holder.title.setText(subject.getTitle());
        holder.teacher.setText(subject.getTeacher());
        holder.room.setText(subject.getRoom());
        holder.time.setText(subject.getTime());
    }
    @Override
    public int getItemCount() {
        return subjectsList.size();
    }
}

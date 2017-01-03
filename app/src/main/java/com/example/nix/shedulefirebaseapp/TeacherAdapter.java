package com.example.nix.shedulefirebaseapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nix on 03.01.2017.
 */

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolderTeacher> {

    private List<TeacherItem> mTeacherItems;

    public static class MyViewHolderTeacher extends RecyclerView.ViewHolder{
        public TextView name, about;
        public MyViewHolderTeacher(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textview_teacher_card);
            about = (TextView) view.findViewById(R.id.textview_teacher_card_descr);
        }
    }
    public TeacherAdapter(List<TeacherItem> teacherList) {
        this.mTeacherItems = teacherList;
    }
    @Override
    public MyViewHolderTeacher onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homework_card, parent, false);
        return new MyViewHolderTeacher(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolderTeacher holder, int position) {
        TeacherItem teacher = mTeacherItems.get(position);
        holder.name.setText(teacher.getName());
        holder.about.setText(teacher.getAbout());
    }
    @Override
    public int getItemCount() {
        return mTeacherItems.size();
    }
}

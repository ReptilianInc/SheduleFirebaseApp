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

class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.MyViewHolderHomeWork> {

    private List<HomeworkItem> mHomeworkItems;

    static class MyViewHolderHomeWork extends RecyclerView.ViewHolder{
        TextView descr;
        MyViewHolderHomeWork(View view) {
            super(view);
            descr = (TextView) view.findViewById(R.id.textview_card_homework);
        }
    }
    public HomeWorkAdapter(List<HomeworkItem> homeworkList) {
        this.mHomeworkItems = homeworkList;
    }
    @Override
    public MyViewHolderHomeWork onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homework_card, parent, false);
        return new MyViewHolderHomeWork(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolderHomeWork holder, int position) {
        HomeworkItem homework = mHomeworkItems.get(position);
        holder.descr.setText(homework.getDescription());
    }
    @Override
    public int getItemCount() {
        return mHomeworkItems.size();
    }
}

package com.example.nix.shedulefirebaseapp;

import java.util.UUID;

/**
 * Created by Nix on 02.01.2017.
 */

public class Subject { //предмет, объект модели
    private String mTitle;
    private String mTime;
    private String mRoom;
    private String mTeacher;
    private int mDay;
    private int mWeek;

    public Subject(){
        //конструктор по умолчанию
    }
    public Subject(String title, String time, String room, String teacher, int day, int week){
        mTitle = title;
        mTime = time;
        mRoom = room;
        mTeacher = teacher;
        mDay = day;
        mWeek = week;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public void setTeacher(String teacher) {
        mTeacher = teacher;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public int getWeek() {
        return mWeek;
    }

    public void setWeek(int week) {
        mWeek = week;
    }
}

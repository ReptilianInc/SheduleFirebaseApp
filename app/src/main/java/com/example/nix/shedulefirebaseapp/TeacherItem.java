package com.example.nix.shedulefirebaseapp;

/**
 * Created by Nix on 03.01.2017.
 */

public class TeacherItem {
    private String mName;
    private String mAbout;

    public TeacherItem(){

    }
    public TeacherItem(String name, String about) {
        mName = name;
        mAbout = about;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String about) {
        mAbout = about;
    }
}

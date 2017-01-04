package com.example.nix.shedulefirebaseapp;

/**
 * Created by Nix on 03.01.2017.
 */

public class HomeworkItem {
    private String mDescription;

    public HomeworkItem() {  //не трогать!!! он нужен для firebase!

    }

    public HomeworkItem(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}

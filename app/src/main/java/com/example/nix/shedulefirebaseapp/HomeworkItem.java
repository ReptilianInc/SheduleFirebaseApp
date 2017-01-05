package com.example.nix.shedulefirebaseapp;

/**
 * Created by Nix on 03.01.2017.
 */

/**
 * лучше тут ничего не трогать. хоть Lint и говорит что это конструктор по умолчанию не используется,
 * но если его убрать, будет ошибка
 */
class HomeworkItem {
    private String mDescription;

    public HomeworkItem() {

    }

    public HomeworkItem(String description) {
        mDescription = description;
    }

    String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}

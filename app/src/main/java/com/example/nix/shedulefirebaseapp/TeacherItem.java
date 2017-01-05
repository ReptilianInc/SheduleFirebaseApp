package com.example.nix.shedulefirebaseapp;

/**
 * Created by Nix on 03.01.2017.
 */
/**
 * лучше тут ничего не трогать. хоть Lint и говорит что это конструктор по умолчанию не используется,
 * но если его убрать, будет ошибка
 */
public class TeacherItem {
    private String mName;
    private String mAbout;

    public TeacherItem(){ //не трогать!!! он нужен для firebase!

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

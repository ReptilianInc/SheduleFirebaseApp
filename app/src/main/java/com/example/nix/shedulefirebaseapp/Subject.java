package com.example.nix.shedulefirebaseapp;


/**
 * Created by Nix on 02.01.2017.
 */
/**
 * лучше тут ничего не трогать. хоть Lint и говорит что это конструктор по умолчанию не используется,
 * но если его убрать, будет ошибка
 */
class Subject { //предмет, объект модели
    private String mTitle;
    private String mTime;
    private String mRoom;
    private String mTeacher;

    public Subject(){ //не трогать!!! он нужен для firebase!
        //конструктор по умолчанию
    }
    public Subject(String title, String time, String room, String teacher){
        mTitle = title;
        mTime = time;
        mRoom = room;
        mTeacher = teacher;
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
}

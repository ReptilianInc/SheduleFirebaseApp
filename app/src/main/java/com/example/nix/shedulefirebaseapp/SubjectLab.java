package com.example.nix.shedulefirebaseapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nix on 02.01.2017.
 */

public class SubjectLab {
    private static SubjectLab sSubjectLab;
    private List<Subject> mSubjects;
    public static SubjectLab get(Context context){
        if(sSubjectLab == null){
            sSubjectLab = new SubjectLab(context);
        }
        return sSubjectLab;
    }

    public List<Subject> getCrimes()
    {
        return mSubjects;
    }

    private SubjectLab(Context context){
        mSubjects = new ArrayList<>();
        Subject subject1 = new Subject("Моделирование систем массового обслуживания", "08:30-10:00", "301A", "Ильин В.А.", 2, 1);
        Subject subject2 = new Subject("Правоведение", "10:10-11:40", "501A", "Иванова В.А.", 2, 1);
        Subject subject3 = new Subject("Базы данных", "11:50-13:20", "301A", "Ололоев В.А.", 2, 1);
        Subject subject4 = new Subject("Базы данных", "13:50-15:20", "301A", "Ололоев В.А.", 2, 1);
        mSubjects.add(subject1);
        mSubjects.add(subject2);
        mSubjects.add(subject3);
        mSubjects.add(subject4);
    }
}

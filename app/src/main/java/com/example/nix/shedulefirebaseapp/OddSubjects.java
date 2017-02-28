package com.example.nix.shedulefirebaseapp;

/**
 * Created by Nix on 03.01.2017.
 */

public class OddSubjects extends AbstractSubjects{
    @Override
    protected void setRoot(int code){
        switch (code){
            case 0:
                ROOT = "mondayodd";
                break;
            case 1:
                ROOT = "tuesdayodd";
                break;
            case 2:
                ROOT = "wednesdayodd";
                break;
            case 3:
                ROOT = "thursdayodd";
                break;
            case 4:
                ROOT = "fridayodd";
                break;
            case 5:
                ROOT = "saturdayodd";
                break;
            default:
                ROOT = "mondayodd";
                break;
        }
    }
}

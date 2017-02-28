package com.example.nix.shedulefirebaseapp;


/**
 * Created by Nix on 03.01.2017.
 */

public class EvenSubjects extends AbstractSubjects {
    @Override
    protected void setRoot(int code){
        switch (code){
            case 0:
                ROOT = "mondayeven";
                break;
            case 1:
                ROOT = "tuesdayeven";
                break;
            case 2:
                ROOT = "wednesdayeven";
                break;
            case 3:
                ROOT = "thursdayeven";
                break;
            case 4:
                ROOT = "fridayeven";
                break;
            case 5:
                ROOT = "saturdayeven";
                break;
            default:
                ROOT = "mondayeven";
                break;
        }
    }
}

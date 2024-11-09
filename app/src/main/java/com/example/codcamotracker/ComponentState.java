package com.example.codcamotracker;

import java.io.Serializable;

public class ComponentState implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean[] checkBoxStates;
    private int progressBarValue;


    public ComponentState(boolean[] checkBoxStates, int progressBarValue) {
        this.checkBoxStates = checkBoxStates;
        this.progressBarValue = progressBarValue;
    }

    public boolean[] getCheckBoxStates() {
        return checkBoxStates;
    }

    public void setCheckBoxStates(boolean[] checkBoxStates) {
        this.checkBoxStates = checkBoxStates;
    }

    public int getProgressBarValue() {
        return progressBarValue;
    }

    public void setProgressBarValue(int progressBarValue) {
        this.progressBarValue = progressBarValue;
    }
}

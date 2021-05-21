package com.example.teledentistry.DoctorModule;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class Slot_Model {
    MaterialRadioButton materialRadioButton;
    String start_time, end_time;

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setMaterialRadioButton(MaterialRadioButton materialRadioButton) {
        this.materialRadioButton = materialRadioButton;
    }

    public String getEnd_time() {
        return end_time;
    }

    public MaterialRadioButton getMaterialRadioButton() {
        return materialRadioButton;
    }

    public String getStart_time() {
        return start_time;
    }

    public Slot_Model(String start_time, String end_time,MaterialRadioButton materialRadioButton) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.materialRadioButton = materialRadioButton;
    }
}

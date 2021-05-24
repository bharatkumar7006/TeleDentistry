package com.example.teledentistry.DoctorModule;

public class BookedSlots_Model {
    String date;
    String full_name;
    String time;
    String pat_id;
    String doc_id;

    public BookedSlots_Model() {
    }

    public BookedSlots_Model(String date, String patient_name, String time,String pat_id) {
        this.date = date;
        this.full_name = patient_name;
        this.time = time;
        this.pat_id = pat_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getPat_id() {
        return pat_id;
    }

    public void setPat_id(String pat_id) {
        this.pat_id = pat_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }



    public BookedSlots_Model(String date, String doc_id, String time) {
        this.date = date;
        this.doc_id = doc_id;
        this.time = time;
    }
}

package com.example.teledentistry.PatientModule;

public class Consultation_Done_Model {
    String date;
    String doc_name;
    String time;
    String pat_id;
    String imageUrl;

    public Consultation_Done_Model() {
    }

    public Consultation_Done_Model(String date, String doc_name, String time, String pat_id, String imageUrl) {
        this.date = date;
        this.doc_name = doc_name;
        this.time = time;
        this.pat_id = pat_id;
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

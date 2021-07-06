package com.example.teledentistry;

public class Consulted_Patient_Model {
    String date;
    String pat_name;
    String time;
    String pat_id;
    String imageUrl;

    public Consulted_Patient_Model() {
    }

    public Consulted_Patient_Model(String date, String pat_name, String time, String pat_id, String imageUrl) {
        this.date = date;
        this.pat_name = pat_name;
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

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
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

package com.example.teledentistry.DoctorModule;

public class Pat_History_Model {
    String date, doc_name, doc_id;

    public Pat_History_Model() {
    }

    public Pat_History_Model(String date, String doc_name, String doc_id) {

        this.date = date;
        this.doc_name = doc_name;
        this.doc_id = doc_id;


    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
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
}

package com.example.teledentistry.DoctorModule;

public class TransectionModel {
    String date, pat_id, disease, fee;

    public TransectionModel() {
    }

    public TransectionModel(String date, String pat_id, String disease, String fee) {
        this.date = date;
        this.pat_id = pat_id;
        this.disease = disease;
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPat_id() {
        return pat_id;
    }

    public void setPat_id(String pat_id) {
        this.pat_id = pat_id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}

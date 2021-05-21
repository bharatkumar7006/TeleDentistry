package com.example.teledentistry.PatientModule;

public class ModelSpecialist {
    String docname, exper, status, fee, slot, soec, sname;
    int iv,fb,consult,appointment,mon,tue,wed,thurs,fri,sat,sun;

    public ModelSpecialist(String docname, String exper, String status, String fee, String slot, String soec, String sname, int iv, int fb, int consult, int appointment, int mon, int tue, int wed, int thurs, int fri, int sat, int sun) {
        this.docname = docname;
        this.exper = exper;
        this.status = status;
        this.fee = fee;
        this.slot = slot;
        this.soec = soec;
        this.sname = sname;
        this.iv = iv;
        this.fb = fb;
        this.consult = consult;
        this.appointment = appointment;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thurs = thurs;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getExper() {
        return exper;
    }

    public void setExper(String exper) {
        this.exper = exper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getSoec() {
        return soec;
    }

    public void setSoec(String soec) {
        this.soec = soec;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

    public int getConsult() {
        return consult;
    }

    public void setConsult(int consult) {
        this.consult = consult;
    }

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }

    public int getTue() {
        return tue;
    }

    public void setTue(int tue) {
        this.tue = tue;
    }

    public int getWed() {
        return wed;
    }

    public void setWed(int wed) {
        this.wed = wed;
    }

    public int getThurs() {
        return thurs;
    }

    public void setThurs(int thurs) {
        this.thurs = thurs;
    }

    public int getFri() {
        return fri;
    }

    public void setFri(int fri) {
        this.fri = fri;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }
}

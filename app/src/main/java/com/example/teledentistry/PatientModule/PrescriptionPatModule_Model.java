package com.example.teledentistry.PatientModule;

import java.util.Collections;

public class PrescriptionPatModule_Model {
    String diagnoses, pat_id,doc_id,tabs;

    public PrescriptionPatModule_Model() {
    }

    public PrescriptionPatModule_Model(String diagnoses, String pat_id, String doc_id, String tabs) {
        this.diagnoses = diagnoses;
        this.pat_id = pat_id;
        this.doc_id = doc_id;
        this.tabs = tabs;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
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

    public String getTabs() {
        return tabs;
    }

    public void setTabs(String tabs) {
        this.tabs = tabs;
    }
}

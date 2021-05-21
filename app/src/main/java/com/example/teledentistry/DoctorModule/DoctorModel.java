package com.example.teledentistry.DoctorModule;

import java.util.HashMap;
import java.util.List;

public class DoctorModel {
    String award;
    String date_of_birth;
    String email;
    String consultation_fee;
    String experience_year;
    String status;
    HashMap<String,List<String>> slots;


    public DoctorModel(String award, String consultation_fee, String experience_year, String experience_month,
                       String expertise, String hospital, String qualificaion, String speciality,
                       String work_address, String address, String city, String account_no, String account_type,
                       String country, String state,String status,HashMap<String,List<String>> slots) {
        this.award = award;
        this.consultation_fee = consultation_fee;
        this.experience_year = experience_year;
        this.experience_month = experience_month;
        this.expertise = expertise;
        this.hospital = hospital;
        this.qualificaion = qualificaion;
        this.speciality = speciality;
        this.work_address = work_address;
        this.address = address;
        this.city = city;
        this.account_no = account_no;
        this.account_type = account_type;
        this.country = country;
        this.state = state;
        this.status = status;
        this.slots = slots;
    }

    public DoctorModel(String date_of_birth, String email, String full_name, String gender, String imageUrl,
                       String marital_status, String password, String phone_no, String practitioner_license,String status,HashMap<String,List<String>> slots) {
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.full_name = full_name;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.marital_status = marital_status;
        this.password = password;
        this.phone_no = phone_no;
        this.practitioner_license = practitioner_license;
        this.status = status;
        this.slots = slots;
    }

    String experience_month;
    String expertise;
    String full_name;
    String gender;
    String hospital;
    String imageUrl;
    String marital_status;
    String password;
    String phone_no;
    String practitioner_license;
    String qualificaion;
    String speciality;
    String work_address;
    String address;
    String city;
    String account_no;
    String account_type;
    String country;
    String state;

    public DoctorModel() {
    }

    public HashMap<String,List<String>> getSlots() {
        return slots;
    }

    public void setSlots(HashMap<String,List<String>> slots) {
        this.slots = slots;
    }

    public DoctorModel(String full_name, String award, String date_of_birth, String email, String consultation_fee,
                       String experience_year, String experience_month, String expertise,
                       String gender, String hospital, String imageUrl, String marital_status, String password,
                       String phone_no, String practitioner_license, String qualificaion, String speciality,
                       String work_address, String address, String city, String account_no, String account_type,
                       String country, String state, String status, HashMap<String,List<String>> slots) {
        this.award = award;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.consultation_fee = consultation_fee;
        this.experience_year = experience_year;
        this.experience_month = experience_month;
        this.expertise = expertise;
        this.full_name = full_name;
        this.gender = gender;
        this.hospital = hospital;
        this.imageUrl = imageUrl;
        this.marital_status = marital_status;
        this.password = password;
        this.phone_no = phone_no;
        this.practitioner_license = practitioner_license;
        this.qualificaion = qualificaion;
        this.speciality = speciality;
        this.work_address = work_address;
        this.address = address;
        this.city = city;
        this.account_no = account_no;
        this.account_type = account_type;
        this.country = country;
        this.state = state;
        this.status = status;
        this.slots = slots;
    }


    public void setAward(String award) {
        this.award = award;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConsultation_fee(String consultation_fee) {
        this.consultation_fee = consultation_fee;
    }

    public void setExperience_year(String experience_year) {
        this.experience_year = experience_year;
    }

    public void setExperience_month(String experience_month) {
        this.experience_month = experience_month;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setPractitioner_license(String practitioner_license) {
        this.practitioner_license = practitioner_license;
    }

    public void setQualificaion(String qualificaion) {
        this.qualificaion = qualificaion;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAward() {
        return award;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public String getConsultation_fee() {
        return consultation_fee;
    }

    public String getExperience_year() {
        return experience_year;
    }

    public String getExperience_month() {
        return experience_month;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getGender() {
        return gender;
    }

    public String getHospital() {
        return hospital;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getPractitioner_license() {
        return practitioner_license;
    }

    public String getQualificaion() {
        return qualificaion;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getWork_address() {
        return work_address;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getAccount_no() {
        return account_no;
    }

    public String getAccount_type() {
        return account_type;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }
}

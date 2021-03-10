package com.example.teledentistry.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Doctor {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "RNumber")
    private String roll;
    @ColumnInfo(name = "Name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
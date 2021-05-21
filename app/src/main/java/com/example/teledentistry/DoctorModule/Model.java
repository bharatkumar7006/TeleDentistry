package com.example.teledentistry.DoctorModule;

import java.io.Serializable;

public class Model implements Serializable {
    private String imageUrl;
    public Model(){}

    public Model(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

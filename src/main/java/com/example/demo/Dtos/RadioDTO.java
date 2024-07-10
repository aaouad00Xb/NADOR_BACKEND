package com.example.demo.Dtos;

import java.util.List;

public class RadioDTO {
    private List<Double> avReel;
    private List<Double> avPgt;
    private List<String> prix;

    public RadioDTO(List<Double> avReel, List<Double> avPgt, List<String> prix) {
        this.avReel = avReel;
        this.avPgt = avPgt;
        this.prix = prix;
    }

    // Add getters and setters for the fields
    public List<Double> getAvReel() {
        return avReel;
    }

    public void setAvReel(List<Double> avReel) {
        this.avReel = avReel;
    }

    public List<Double> getAvPgt() {
        return avPgt;
    }

    public void setAvPgt(List<Double> avPgt) {
        this.avPgt = avPgt;
    }

    public List<String> getPrix() {
        return prix;
    }

    public void setPrix(List<String> prix) {
        this.prix = prix;
    }
}

package com.example.demo.Dtos;

public class SituationDto {

    public SituationDto(Double avReel, Double avPgt, String objet) {
        this.avReel = avReel;
        this.avPgt = avPgt;
        this.objet = objet;
    }

    public Double getAvReel() {
        return avReel;
    }

    public void setAvReel(Double avReel) {
        this.avReel = avReel;
    }

    public Double getAvPgt() {
        return avPgt;
    }

    public void setAvPgt(Double avPgt) {
        this.avPgt = avPgt;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    private  Double avReel;
    private Double avPgt;
    private String objet;
}

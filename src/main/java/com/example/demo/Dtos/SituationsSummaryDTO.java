package com.example.demo.Dtos;

import java.util.List;

public class SituationsSummaryDTO {
    private List<Double> avReel;
    private List<Double> avPgt;
    private List<String> dates;

    public SituationsSummaryDTO(List<Double> avReel, List<Double> avPgt, List<String> dates) {
        this.avReel = avReel;
        this.avPgt = avPgt;
        this.dates = dates;
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

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}

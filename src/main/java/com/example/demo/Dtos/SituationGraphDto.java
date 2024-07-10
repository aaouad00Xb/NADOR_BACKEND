package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituationGraphDto {

    private List<String> formatedDates;
    private List<Double> orderedSituationValues;
    private List<Double> orderedSituationRValues;
    private List<Double> orderedSituationFValues;
    private List<Double> orderedSituationRFValues;
}

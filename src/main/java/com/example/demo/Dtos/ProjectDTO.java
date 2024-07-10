package com.example.demo.Dtos;

import com.example.demo.Entities.Projet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectDTO {

    private Projet myProject;

    private  Double avReel;
    private Double avPgt;
    List<LotDto> lotDtos;

//calcul
    private  Double Reel;
    private Double Pgt;
    private Double montant;


    // Getters and setters
}
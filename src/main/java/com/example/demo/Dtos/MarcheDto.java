package com.example.demo.Dtos;

import com.example.demo.Entities.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MarcheDto {
    private Long id_marche;

    private String numero_marche;
    private Integer delai;
    private String objet;
    private String titulaire;
    private Double montant;
    private Date os_commencement;
    private Date dateOverturePlit;
    private String n_appel_offre;
    private Double estimationao;
    private Double montantengage;
    private String etape_etude;
    private String status_etude;
    private String delegation;
    private String status_marche;
    private Situation situation;

    private  Double avReel;
    private Double avPgt;

}

package com.example.demo.Dtos;

import com.example.demo.Entities.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrestationAcquisitionDto {

    private Long nombre;

    private String type;

    private Date dateOuverturePlis;

    private Province province;

    private Double montant;

    private String titulaire;

    private Date osc;

    private Long delais;
    private Long provinceId;

    private Date dateLivraison;

    private String commentaire;

}

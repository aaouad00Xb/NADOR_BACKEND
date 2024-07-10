package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PrestationAcquisition {
    @Id
    @GeneratedValue
    private Long id;

    private Long nombre;

    @Column(columnDefinition = "Text")
    private String type;

    private Date dateOuverturePlis;

    //todo
    @ManyToOne()
    private Province province;

    private Double montant;

    @Column(columnDefinition = "Text")
    private String titulaire;

    private Date osc;

    private Long delais;

    private Date dateLivraison;

    @Column(columnDefinition = "Text")
    private String commentaire;


}

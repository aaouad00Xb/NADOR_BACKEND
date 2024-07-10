package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SituationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_situation_detail", nullable = false)
    private Long id_situation_detail;

//    private Integer numprix;
//    private String prix;
//    private String unite;
//    private Double qte;
//    private Double pu;
//    private Double montant;

    private Double qteprecu ;
    private Double qtereacu ;
    private Double montapgt ;
    private Double montreal ;
    private Double avancpgt ;
    private Double avanreal ;
    private Double ecart ;
    private Double qtecalcu ;
    private Double pucalcul ;
    private Date datedemarage ;


//    @JsonIgnoreProperties({"marche"})
//    @ManyToOne(cascade={CascadeType.MERGE})
//    @JoinColumn(name = "prix")
//    private Prix prix;



}

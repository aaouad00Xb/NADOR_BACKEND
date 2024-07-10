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
public class MarcheAnnexes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    private String titulaire;
    private Double montantEngage;
    private Date dateaprobation;
    private String autoriteaprobation;
    private String type;
    @Column(columnDefinition = "Text")
    private String commentaire;

}

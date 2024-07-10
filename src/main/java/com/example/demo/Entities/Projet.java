package com.example.demo.Entities;

import com.example.demo.Enums.PriotaireType;
import com.example.demo.Enums.ProgrammeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String objet;
    private String motcle;
    private String observation;
    private Integer anneeProjet;


    private String liaisonDepart;
    private String liaisonArrive;


    @JsonIgnoreProperties({"projets"})
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "niveau")
    private Niveau niveau;


    @JsonIgnoreProperties({"projet"})
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "projet_id")
    private Set<Section> sections;

    @Enumerated(EnumType.STRING)
    private ProgrammeType programmeType;

    private String priotaireType;

}

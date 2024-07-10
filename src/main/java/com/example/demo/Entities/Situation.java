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
public class  Situation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_situation", nullable = false)
    private Long id_situation;

//    private Date datesituation;
    private String obervation;
    private Double avancementPGT;
    private Double avancementReel;


    private Date datesituation;
    private Double avancement_physique;
    private Double avancement_fenancier;

    @Column(columnDefinition = "boolean default false")
    private boolean validated;
//    @OneToMany(  cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<SituationDetail> situationdetail;

}

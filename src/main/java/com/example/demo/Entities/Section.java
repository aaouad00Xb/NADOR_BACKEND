package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_section", nullable = false)
    private Long id_section;

    private  String intitule;
    private  String observation;
    private  Double  pkd ;
    private  Double  pkf ;
//    private  String  type ;

    private  String  categorie ;
    private  String  numero ;

    private String liaisonDepart;
    private String liaisonArrive;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province")
    private Province province;


    @Column(columnDefinition = "Text")
    private  String  franchissement  ;


    @Column(columnDefinition = "Text")
    private  String  commentaire;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Commune> communes;

    @JsonIgnoreProperties({"projet"})
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Set<Lot> lots;




//    @Column(columnDefinition = "Text")
//    private  String  franchissement  ;
//
//    @Transient
//    private String geom;
//
//
////    @ManyToOne(cascade=CascadeType.MERGE)
////    @JoinColumn(name = "province")
////    private Province province;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "province")
//    private Province province;

}

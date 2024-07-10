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
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private  String intitule;
    private  Double  pkd ;
    private  Double  pkf ;
//    private  Double  montant;

// just added to the context
//    private  String  categorie ;
//    private  String  numero ;
    private  String  type ;

    @Column(columnDefinition = "Text")
    private  String  impact ;

    @Column(columnDefinition = "Text")
    private  String  franchissement  ;



//    @ManyToOne(cascade=CascadeType.MERGE)
//    @JoinColumn(name = "province")
//    private Province province;



    @JsonIgnoreProperties({"projet"})
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Set<Marche> marches;


//    @JsonIgnoreProperties({"lots"})
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "marche")




//    @JsonIgnoreProperties({"lot"})
//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "marche")
//    private Marche marche;





}

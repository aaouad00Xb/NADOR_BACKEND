package com.example.demo.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Marche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_marche", nullable = false)
    private Long id_marche;

    private String numero_marche;
    private Integer delai;
    private Integer delaiModifie;
    @Enumerated(EnumType.STRING)
    private Motif motif;
    private String objet;
    private String titulaire;
    private Double montant;
    private Date os_commencement;
    private Date dateOverturePlit;
    private Date dateprevuAchevement;
    private String n_appel_offre;

    private Double estimationao;

    private Double montantengage;
    private Double prixGlobal;

    @Column(columnDefinition = "boolean default false")
    private boolean etudeGeothe;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum values as strings
    private PrestationType type;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum values as strings
    private Categorie categorie;

    private String etape_etude;
    private String status_etude;
    private String delegation;
    private String status_marche;

    private Long nombre;
    private Long cp;
    private Long ce;
    private String aquisition_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date etablieLe;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appreuveLe;

    @Column(columnDefinition = "Text")
    private String commentaire;

//for all Of them traveaux

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appel_offre_p;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_travaux_p;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date avancement_etudes_p;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_Etude_AP;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_Etude_EG;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_Etude_ED;

//reel

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appel_offre_r;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_travaux_r;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date avancement_etudes_r;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_Etude_AP_r;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_Etude_EG_r;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marche_Etude_ED_r;






//    @OneToMany(mappedBy = "marche", cascade = CascadeType.ALL, fetch = FetchType.LAZY)


//    @JsonIgnoreProperties({"marche"})
//    @OneToMany( cascade = { CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    private List<Prix> prixes = new ArrayList<>();

    //for traveaux
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "prestationID")
    private List<Situation> situations = new ArrayList<>();

    @JoinColumn(name = "prestationID")
    @OneToMany( cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Situation_r> situations_r = new ArrayList<>();



    @JoinColumn(name = "prestationID")
    @OneToMany( cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<MarcheAnnexes> marcheAnnexes = new ArrayList<>();

}


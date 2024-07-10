package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Prix {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prix", nullable = false)
    private Long id_prix;

    private Integer numprix;
    private String prix;
    private String unite;
    private Double qte;
    private Double pu;
    private Double montant;

    @JsonIgnoreProperties({"prix"})
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "marche")
    private Marche marche;
}

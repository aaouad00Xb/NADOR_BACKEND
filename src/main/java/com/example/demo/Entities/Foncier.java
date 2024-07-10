package com.example.demo.Entities;

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
public class Foncier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;


//    @JsonIgnoreProperties({"foncier"})
//    @OneToMany(mappedBy = "foncier", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    private List<Projet> projets = new ArrayList<>();



}
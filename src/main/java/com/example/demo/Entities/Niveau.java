package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public class Niveau {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private Long id;
        private String description;

        @JsonIgnoreProperties({"niveau"})
        @OneToMany(mappedBy = "niveau", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
        private List<Projet> projets = new ArrayList<>();



    }

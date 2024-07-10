package com.example.demo.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_province", nullable = false)
    private Long id_province;
    private String name;

//    @ManyToMany(mappedBy = "provinces")
//    @JsonBackReference
//    Set<Projet> projet;



    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Id_Province")
    private List<Commune> communes;

}

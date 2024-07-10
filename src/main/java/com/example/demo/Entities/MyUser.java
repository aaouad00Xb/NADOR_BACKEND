package com.example.demo.Entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sex;
    private String cin;
    private String email;
    private String image;
    private String firstName;
    private String lastName;
    private String userPhone;
    private String userCodeNumber;
    private Date lastConnect;
    private Date birthday;
    private String username;
    private String password ;
    @Enumerated(EnumType.STRING)

    private ERole role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "province_id") // Assuming the name of the foreign key column is "province_id"
    private Province province;

//    @ManyToMany(fetch= FetchType.LAZY,cascade = CascadeType.MERGE)
//    private Collection<Role> roles = new ArrayList<>();

//    @ManyToOne(fetch= FetchType.EAGER,cascade = CascadeType.ALL)
//    Profil profil;

}

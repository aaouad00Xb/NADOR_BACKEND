package com.example.demo.Service;

import com.example.demo.Dao.Niveau_Repo;
import com.example.demo.Entities.Niveau;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class NiveauService {


    @Autowired
    private Niveau_Repo niveauRepo;


    public Optional<Niveau> findNiveauById(Long id){
        return this.niveauRepo.findById(id);
    }

    public List<Niveau> findAll() {
        return this.niveauRepo.findAll();

    }
}

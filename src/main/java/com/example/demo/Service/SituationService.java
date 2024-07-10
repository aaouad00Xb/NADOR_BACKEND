package com.example.demo.Service;

import com.example.demo.Dao.Situation_Repo;
import com.example.demo.Entities.Situation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class SituationService {
    @Autowired
    private Situation_Repo situationRepo;


    public Situation saveSituation(Situation situation) {
        return situationRepo.save(situation);
    }

    public List<Situation> saveSituations(List<Situation> situation) {
        return situationRepo.saveAll(situation);
    }



}

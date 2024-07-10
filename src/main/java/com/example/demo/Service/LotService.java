package com.example.demo.Service;


import com.example.demo.Dao.Lot_Repo;
import com.example.demo.Entities.Lot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class LotService {

    @Autowired
    private Lot_Repo lotRepo;


    public Optional<Lot> findLotById(Long id){
        return this.lotRepo.findById(id);
    }

}

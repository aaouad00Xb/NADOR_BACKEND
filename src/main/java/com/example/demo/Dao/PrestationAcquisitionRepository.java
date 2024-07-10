package com.example.demo.Dao;

import com.example.demo.Entities.Personnel;
import com.example.demo.Entities.PrestationAcquisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationAcquisitionRepository  extends JpaRepository<PrestationAcquisition,Long> {
}

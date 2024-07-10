package com.example.demo.Dao;

import com.example.demo.Entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository  extends JpaRepository<Commune,Long> {
}

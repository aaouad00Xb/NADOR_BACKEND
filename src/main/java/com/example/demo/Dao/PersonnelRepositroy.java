package com.example.demo.Dao;

import com.example.demo.Entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepositroy extends JpaRepository<Personnel,Long> {


//    @Query(value="select a.entreprise,a.pk_debut,a.pk_fin,ST_AsGeoJSON(ST_LineSubstring(ST_LineMerge(p.geom), a.pk_debut/65.750,a.pk_fin/65.750)) from projet p,avancement a" , nativeQuery = true)
//    List<Map<String,?>> getavancements();

//    @Query(value="select * from spliteF(:id,:pourcent)" , nativeQuery = true)
//    Integer spliteF(@Param("id") Integer id,@Param("pourcent") Double pourcent);

}

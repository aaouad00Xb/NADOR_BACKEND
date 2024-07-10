package com.example.demo.Dao;

import com.example.demo.Entities.Projet;
import com.example.demo.Enums.ProgrammeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Projet_Repo extends JpaRepository<Projet,Long>  {


    @Query(nativeQuery = true,
            value = "update section set geom = ST_GeomFromGeoJSON(:geom1) where id_section = :sec_id  ")
            void saveGeom(@Param("sec_id") Long sec_id,@Param("geom1") String geom1);

    @Query(value = "SELECT CAST(jsonb_build_object(\n" +
            "    'type', 'Feature',\n" +
            "    'properties', jsonb_build_object('intitule', l.intitule),\n" +
            "    'geometry', ST_AsGeoJSON(s.geom)\n" +
            ") AS TEXT) AS json_result\n" +
            "FROM section s\n" +
            "JOIN lot_sections ps ON s.id_section = ps.sections_id_section\n" +
            "JOIN projet_lots pl ON ps.lot_id = pl.lots_id\n" +
            "JOIN lot l ON l.id = pl.lots_id\n" +
            "WHERE projet_id = :projectId ", nativeQuery = true)
    List<String>getGeomProjet(@Param("projectId") Long projectId);


    @Query(value = "SELECT CAST(jsonb_build_object(\n" +
            "          'type', 'Feature',\n" +
            "           'properties', jsonb_build_object('intitule', l.intitule,'type',s.type,'pkd',s.pkd,'pkf',s.pkf,'lots_id',pl.lots_id,'length',ST_Length(s.geom) ),\n" +
            "         'geometry', ST_AsGeoJSON(s.geom)\n" +
            "         ) AS TEXT) AS json_result\n" +
            "            FROM section s\n" +
            "            JOIN lot_sections ps ON s.id_section = ps.sections_id_section\n" +
            "           JOIN projet_lots pl ON ps.lot_id = pl.lots_id\n" +
            "            JOIN lot l ON l.id = pl.lots_id\n" +
            "           WHERE projet_id = :projectId ", nativeQuery = true)
    List<String>getGeomProjetwithSomeAdditionalData(@Param("projectId") Long projectId);




    @Query("SELECT p FROM Projet p WHERE p.programmeType = :programmeType")
    List<Projet> findAllByProgrammeType(@Param("programmeType") ProgrammeType programmeType);

    @Query("SELECT p FROM Projet p JOIN p.sections s WHERE p.programmeType = :programmeType AND s.province.id_province = :provinceId")
    List<Projet> findAllByProgrammeTypeAndProvinceId(@Param("programmeType") ProgrammeType programmeType, @Param("provinceId") Long provinceId);
    @Query(value = "SELECT DISTINCT p.*\n" +
            "FROM Projet p\n" +
            "JOIN Section s ON p.id = s.projet_id\n" +
            "JOIN Lot l ON s.id_section = l.section_id\n" +
            "JOIN marche m ON m.lot_id = l.id\n" +
            "JOIN Situation st ON m.id_marche = st.prestationid\n" +
            "WHERE st.validated = false",nativeQuery = true)
    List<Projet> findInvalidatedProjects();

}

package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Entities.*;
import com.example.demo.Enums.ProgrammeType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private Lot_Repo lotRepo;
    private PersonnelRepositroy personnelRepositroy;
    private Projet_Repo projetRepository;
    private Marche_Repo marcheRepository;
    private Lot_Repo lotRepository;
    private Foncier_Repo foncierRepo;
    private Province_Repo provinceRepo;
    private Niveau_Repo niveauRepo;
    private Province_Repo provinceRepository;
private Section_Repo sectionRepository;
    @Autowired
    public Service(Lot_Repo lotRepo, PersonnelRepositroy personnelRepositroy, Projet_Repo projetRepository, Marche_Repo marcheRepository, Lot_Repo lotRepository, Foncier_Repo foncierRepo, Province_Repo provinceRepo, Niveau_Repo niveauRepo, Province_Repo provinceRepository, Section_Repo sectionRepository) {
        this.lotRepo = lotRepo;
        this.personnelRepositroy = personnelRepositroy;
        this.projetRepository = projetRepository;
        this.marcheRepository = marcheRepository;
        this.lotRepository = lotRepository;
        this.foncierRepo = foncierRepo;
        this.provinceRepo = provinceRepo;
        this.niveauRepo = niveauRepo;
        this.provinceRepository = provinceRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<Projet> getAll(){
        return this.projetRepository.findAll();
    }
    public List<Province> getProvinecs(){
        return this.provinceRepo.findAll();
    }
        public List<Foncier> getFonciers(){
        return this.foncierRepo.findAll();
    }

    public List<Niveau> getNiveau(){
        return this.niveauRepo.findAll();
    }

    public List<Projet> getProjets(){
        return this.projetRepository.findAllByProgrammeType(ProgrammeType.Urgent);
    }

    public List<Projet> getProjetsP(){
        return this.projetRepository.findAllByProgrammeType(ProgrammeType.prioritaire);
    }


    public List<Projet> getProjetsD(){
        return this.projetRepository.findAllByProgrammeType(ProgrammeType.developpement);
    }

    public Projet saveProjet(Projet projet){
        Projet p = this.projetRepository.save(projet);


        return this.projetRepository.save(p);

    }


    public Lot saveLot(Lot lot){

        return this.lotRepo.save(lot);
    }

    public Section saveSection(Section section){

        return this.sectionRepository.save(section);
    }

    public  List<String> getGeomProjet(Long id_projet) {
        try {
            return this.projetRepository.getGeomProjet(id_projet);
        } catch (Exception e) {

        }
    return null;
    }


 public  List<String> getGeomProjetwithSomeAdditionalData(Long id_projet) {
        try {
            return this.projetRepository.getGeomProjetwithSomeAdditionalData(id_projet);
        } catch (Exception e) {

        }
    return null;
    }






    public void saveGeom(PersistgeomSections projet){
        try{
             this.projetRepository.saveGeom(projet.getSection(),projet.getGeometry());
        }catch ( Exception e ){

        }
    }

    public Personnel ajoutPer(){
        return this.personnelRepositroy.save(Personnel.builder().name("mohammed").data("achraf").build());
    }
}

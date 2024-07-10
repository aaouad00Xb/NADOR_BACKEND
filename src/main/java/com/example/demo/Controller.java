package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.Dao.SituationDetail_Repo;
import com.example.demo.Dtos.*;
import com.example.demo.Entities.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "rest")

public class Controller {

    @Autowired
    public Service service;


    @Autowired
    private  UserService userService;

    @Autowired
    public MarcheService marcheService;


    @Autowired
    public PrixService prixService;

    @Autowired
    public SituationService situationService;


    @Autowired
    public ProjectService projectService;


    @Autowired
    public NiveauService niveauService;


    @Autowired
    public LotService lotService;

    @Autowired
    public SituationDetail_Repo situationDetail_repo;


    //endpoint get
    @GetMapping(value = "/")

    public ResponseEntity<?> index() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/")

    public ResponseEntity<?> index2(@RequestBody String test) {
        return new ResponseEntity<>(test, HttpStatus.OK);
    }


    @PostMapping(value = "/saveProject")

    public ResponseEntity<?> saveProject(@RequestBody Projet projet) {

        Projet p = this.service.saveProjet(projet);
        //todo
//        for (Lot l : p.getLots()) {
//            for (Section s : l.getSections()) {
//                this.service.saveGeom(PersistgeomSections.builder().section(s.getId_section()).geometry(s.getGeom()).build());
//            }
//        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


    @PostMapping(value = "/getGeomProjet",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> getGeomProjet(@RequestBody Projet projet) {

        return new ResponseEntity<>(this.service.getGeomProjet(projet.getId()), HttpStatus.OK);
    }

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorisqtionHeader = request.getHeader(AUTHORIZATION);
        if(authorisqtionHeader != null && authorisqtionHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorisqtionHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                MyUser user = userService.getUser(username);

                return new ResponseEntity<>(user,HttpStatus.OK);

            }catch (Exception e){

            }
        }else {
            throw new RuntimeException("refresh tocken is missing");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/users")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }






    @PostMapping(value = "/getGeomProjetwithSomeAdditionalData",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> getGeomProjetwithSomeAdditionalData(@RequestBody Projet projet) {

        return new ResponseEntity<>(this.service.getGeomProjetwithSomeAdditionalData(projet.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/saveMarchetoLot")
    public ResponseEntity<?> saveMarchetoLot(@RequestBody Lot lot) {

        return new ResponseEntity<>(this.service.saveLot(lot), HttpStatus.OK);
    }


    @PostMapping(value = "/saveSection")
    public ResponseEntity<?> saveSection(@RequestBody Section section) {

        return new ResponseEntity<>(this.service.saveSection(section), HttpStatus.OK);
    }
    @GetMapping(value = "/addPersonnel")
    public ResponseEntity<?> addPersonnel() {
        return new ResponseEntity<>(this.service.ajoutPer(), HttpStatus.OK);
    }

    @GetMapping(value = "/getProvinecs")

    public ResponseEntity<?> getProvinecs() {
        return new ResponseEntity<>(this.service.getProvinecs(), HttpStatus.OK);
    }

    @GetMapping(value = "/getFonciers")

    public ResponseEntity<?> getFonciers() {
        return new ResponseEntity<>(this.service.getFonciers(), HttpStatus.OK);
    }


    @GetMapping(value = "/getNiveau")

    public ResponseEntity<?> getNiveau() {
        return new ResponseEntity<>(this.service.getNiveau(), HttpStatus.OK);
    }
    @GetMapping(value = "/getProjets")

    public ResponseEntity<?> getProjets() {
        return new ResponseEntity<>(this.service.getProjets(), HttpStatus.OK);
    }
      @GetMapping(value = "/getProjetsD")
        public ResponseEntity<?> getProjetsD() {
            return new ResponseEntity<>(this.service.getProjetsD(), HttpStatus.OK);
        }
      @GetMapping(value = "/getProjetsP")
        public ResponseEntity<?> getProjetsP() {
            return new ResponseEntity<>(this.service.getProjetsP(), HttpStatus.OK);
        }


    @PostMapping(value = "/getLastSituationForMarche")
    public ResponseEntity<?> getLastSituationForMarche(@RequestBody Marche marche) {
        Situation_r lastSituation = marcheService.getLastOrFutureSituationRForMarche(marche);
        if (lastSituation == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lastSituation);
    }


    @PostMapping(value = "/saveLassatSituationForMarche/{marcheId}")
    public ResponseEntity<?> getLastSituationForMarche(@PathVariable Long marcheId , @RequestBody Situation situation) {
        marcheService.saveSituationtoMarche(situation,marcheId);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(value = "/getGraphs/{marcheId}")
    public ResponseEntity<?> getGraphs(@PathVariable Long marcheId) {

        return new ResponseEntity<>(marcheService.getGraphs(marcheId),HttpStatus.OK);
    }


    @GetMapping(value = "/getavancementDesIndicateursP/{marcheId}")
    public ResponseEntity<?> getavancementDesIndicateursP(@PathVariable Long marcheId) {
        return new ResponseEntity<>(marcheService.getavancementDesIndicateursP(marcheId),HttpStatus.OK);
    }


    @PostMapping("/saveSituation")
    public ResponseEntity<Situation> saveSituation(@RequestBody Situation situation) {
        Situation savedSituation = situationService.saveSituation(situation);
        return ResponseEntity.ok(savedSituation);
    }


    @PostMapping("/saveMarche")

    public ResponseEntity<Marche> saveMarche(@RequestBody Marche marche) {

        List<Situation> st = this.situationService.saveSituations(marche.getSituations());
        marche.setSituations(st);
        Marche savedMarche = marcheService.saveMarche(marche);


        return ResponseEntity.ok(savedMarche);
    }

    @PostMapping("/saveMarcheAfterInit")

    public ResponseEntity<Marche> saveMarcheAfterInit(@RequestBody Marche marche) {


//        List<Prix> prx = this.prixService.saveAll(marche.getPrixes());
//        marche.setPrixes(prx);
        Marche savedMarche = marcheService.updateSituation(marche);

//        for (Prix p : savedMarche.getPrixes()) {
//            p.setMarche(savedMarche);
//        }
//
//        this.prixService.saveAll(savedMarche.getPrixes());

        return ResponseEntity.ok(savedMarche);
    }


//    @GetMapping("/getEvolutionAvancement/{marcheId}")
//
//    public SituationsSummaryDTO getEvolutionAvancement(@PathVariable Long marcheId) {
//
//        if (marcheId != null) {
//            return this.marcheService.getEvolutionAvancement(marcheId);
//        }
//        return null;
//
//
//    }

    @GetMapping("/getRadar/{marcheId}")

    public RadioDTO getRadar(@PathVariable Long marcheId) {

        if (marcheId != null) {
            return this.marcheService.getRadar(marcheId);
        }
        return null;
    }

//    @GetMapping("/getSituationGlobal")
//    public ResponseEntity<?> getSituationGlobal(@RequestParam(required = false) Long niveauId, @RequestParam(required = false) Long projetId, @RequestParam(required = false) Long lotId, @RequestParam(required = false) Long marcheId) {
//
//
//
//
//
//        if (marcheId != null ) {
//            return new ResponseEntity<>(this.marcheService.getStuationMarche(marcheId),HttpStatus.OK);
//        } else if (lotId != null) {
//
//
//            List<Long> marchesId = new ArrayList<>();
//
//            Lot lot = this.lotService.findLotById(lotId).get();
//            for (Marche m : lot.getMarches()) {
//                marchesId.add(m.getId_marche());
//
//            }
//
//            LotDto lotDto = this.marcheService.getStuationLot(marchesId);
//            lotDto.setMyLot(lot);
//
//            return new ResponseEntity<>(lotDto,HttpStatus.OK);
//
//        } else if (projetId != null) {
//            Projet p = this.projectService.findprojetById(projetId).get();
//
//
//            return new ResponseEntity<>( this.marcheService.calculate(p),HttpStatus.OK);
//
//        }else if (niveauId != null) {
//            Niveau n = this.niveauService.findNiveauById(niveauId).get();
//            return new ResponseEntity<>(this.marcheService.calculateForNiveau(n),HttpStatus.OK);
//
//        }else{
//            List<Niveau> niveaus = this.niveauService.findAll();
//            List<NiveauDto> niveauDtos = new ArrayList<>();
//            for(Niveau n:niveaus){
//                niveauDtos.add(this.marcheService.calculateForNiveau(n));
//            }
//
//            return new ResponseEntity<>(niveauDtos,HttpStatus.OK);
//
//        }
//    }




//    @GetMapping("/test")
//    public NiveauDto test() {
//        Niveau n = this.niveauService.findNiveauById(1L).get();
//        return this.marcheService.calculateForNiveau(n);
//
//    }


    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(@RequestBody MyUser user){
        this.userService.saveUser(user);
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }


    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody MyUser myUser){

        return new ResponseEntity<>(this.userService.save(myUser),HttpStatus.OK);
    }








}

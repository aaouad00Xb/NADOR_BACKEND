package com.example.demo.Service;


import com.example.demo.Dao.*;
import com.example.demo.Dtos.*;
import com.example.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class MarcheService {


    private Marche_Repo marche_repo;
    private Situation_Repo situationRepo;

    @Autowired
    public MarcheService(Marche_Repo marcheRepo, Situation_Repo situationRepo) {
        marche_repo = marcheRepo;
        this.situationRepo = situationRepo;
    }


    public Marche updateSituation(Marche marche){

        Marche m_p = marche_repo.findById(marche.getId_marche()).get();

            if(marche.getDelegation()!=null
                    && "approuvé".equals(marche.getStatus_marche())
                    && marche.getAppel_offre_r()==null
            ){
                marche.setAppel_offre_r(new Date());
            }
            if(marche.getOs_commencement()!=null && m_p.getOs_commencement()==null){
                marche.setMarche_travaux_r(new Date());
            }
            //etude
        if(marche.getEtape_etude() != null){
            switch (marche.getEtape_etude()){
                case "ED":
                    if(marche.getMarche_Etude_ED_r() == null && marche.getStatus_etude().equals("approuvé")) marche.setMarche_Etude_ED_r(new Date());
                    break;
                case "EG":
                    if(marche.getMarche_Etude_EG_r() == null&& marche.getStatus_etude().equals("approuvé")) marche.setMarche_Etude_EG_r(new Date());
                    break;
                case "AP":
                    if(marche.getMarche_Etude_AP_r() == null&& marche.getStatus_etude().equals("approuvé")) marche.setMarche_Etude_AP_r(new Date());
                    break;
                case "PE":
                    if(marche.getAvancement_etudes_r() == null && marche.getStatus_etude().equals("approuvé")) marche.setAvancement_etudes_r(new Date());
                    break;
                default:
                    System.out.println("pas d'avancement saisie");
            }

        }



        return marche_repo.save(marche);
    }





    public Situation_r getLastOrFutureSituationRForMarche(Marche marche) {
        List<Situation_r> situations = marche.getSituations_r();

        if (situations == null || situations.isEmpty()) {
            return Situation_r.builder().avancement_physique(0.0).avancement_fenancier(0.0).datesituation(new Date()).build();
        }

        Date today = new Date();

        // Find the first Situation with a date greater than today
        Situation_r futureSituation = situations.stream()
                .filter(situationR -> situationR.getDatesituation()!= null)
                .filter(s -> s.getDatesituation().after(today))
                .min(Comparator.comparing(Situation_r::getDatesituation))
                .orElse(null);

        if (futureSituation != null) {
            return futureSituation;
        }

        // If no future situation is found, return the last situation
        Situation_r lastSituation = situations.stream()
                .filter(situationR -> situationR.getDatesituation()!= null)
                .max(Comparator.comparing(Situation_r::getDatesituation))
                .orElse(null);

        return lastSituation;
    }



    public Situation getLastOrFutureSituationForMarche(Marche marche) {
        List<Situation> situations = marche.getSituations();

        if (situations == null || situations.isEmpty()) {
            return Situation.builder().avancement_physique(0.0).avancement_fenancier(0.0).datesituation(new Date()).build();
        }

        Date today = new Date();

        // Find the first Situation with a date greater than today
        Situation futureSituation = situations.stream()
                .filter(situationR -> situationR.getDatesituation()!= null)
                .filter(situationR -> situationR.isValidated())

                .filter(s -> s.getDatesituation().after(today))
                .min(Comparator.comparing(Situation::getDatesituation))
                .orElse(null);

        if (futureSituation != null) {
            return futureSituation;
        }

        // If no future situation is found, return the last situation
        Situation lastSituation = situations.stream()
                .filter(situationR -> situationR.getDatesituation()!= null)
                .filter(situationR -> situationR.isValidated())

                .max(Comparator.comparing(Situation::getDatesituation))
                .orElse(null);

        return lastSituation;
    }




    public void saveSituationtoMarche(Situation situation , Long marcheID) {

        try {

          Marche marche =  marche_repo.findById(marcheID).orElseThrow(()->new RuntimeException("marche not found"));

          Situation s = situationRepo.save(situation);
          marche.getSituations().add(s);

          marche_repo.save(marche);
        }catch (Exception e){

            throw new RuntimeException(e.getMessage());
        }

    }



    public SituationGraphDto getGraphs(Long marcheID) {

        try {
            Marche marche = marche_repo.findById(marcheID).orElseThrow(() -> new RuntimeException("Marche not found"));

            Set<Date> mesDatesSet = new HashSet<>();
//            List<Double> situationValues = new ArrayList<>();
//            List<Double> situationRValues = new ArrayList<>();
//            List<Double> situationFValues = new ArrayList<>();
//            List<Double> situationRFValues = new ArrayList<>();

            for (Situation s : marche.getSituations()) {
                if (s.getDatesituation() != null) {
                    mesDatesSet.add(s.getDatesituation());
                }
            }

            for (Situation_r s : marche.getSituations_r()) {
                if (s.getDatesituation() != null) {
                    mesDatesSet.add(s.getDatesituation());
                }
            }




            // Convert set to list and order by date
            List<Date> orderedDates = mesDatesSet.stream()
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            List<String> formatedDates = orderedDates.stream()
                    .map(d-> getformatedDate(d))
                    .collect(Collectors.toList());

            List<Date> mesDates = new ArrayList(mesDatesSet);

            ValueHolder previous_value = new ValueHolder(null); // Initialize to null


            // Order the lists corresponding to Situation values by date

            List<Double> orderedSituationValues = new ArrayList<>();

            orderedDates.stream().forEach(
                    date -> {
                        boolean found = false; // Flag to check if situation is found for the given date


                        for (Situation s: marche.getSituations()){
                            if(date.equals(s.getDatesituation()) && s.isValidated() ){
                                orderedSituationValues.add(s.getAvancement_physique());
                                previous_value.setValue(s.getAvancement_physique());
                                found = true;
                                break;
                            }
                        }

                        // If situation is not found for the given date, add null
                        if (!found) {
                            orderedSituationValues.add(previous_value.getValue());
                        }
                    }
            );

//            List<Double> orderedSituationValues = orderedDates.stream()
//                    .map(date -> {
//                        int index = mesDates.indexOf(date);
//
//                        if(index != -1 && !situationValues.isEmpty() && situationValues.size()>(index)){
//                            previous_value.setValue(situationValues.get(index));
//                        }
//
//                        return index != -1 && !situationValues.isEmpty() && situationValues.size()>(index) ? situationValues.get(index) : previous_value.getValue();
//                    })
//                    .collect(Collectors.toList());



            ValueHolder previous_value1 = new ValueHolder(null); // Initialize to null


//            referece for physiques
            List<Double> orderedSituationRValues = new ArrayList<>();

            orderedDates.stream().forEach(
                    date -> {
                        boolean found = false; // Flag to check if situation is found for the given date


                        for (Situation_r s: marche.getSituations_r()){
                            if(date.equals(s.getDatesituation())){
                                orderedSituationRValues.add(s.getAvancement_physique());
                                previous_value1.setValue(s.getAvancement_physique());
                                found = true;
                                break;
                            }
                        }

                        // If situation is not found for the given date, add null
                        if (!found) {
                            orderedSituationRValues.add(previous_value1.getValue());
                        }
                    }
            );


            // Order the lists corresponding to Situation_r values by date
//            List<Double> orderedSituationRValues = orderedDates.stream()
//                    .map(date -> {
//                        int index = mesDates.indexOf(date);
//                        if(index != -1 && !situationRValues.isEmpty() && situationRValues.size()>(index)){
//                            previous_value1.setValue(situationRValues.get(index));
//                        }
//                        return index != -1 && !situationRValues.isEmpty() && situationRValues.size()>(index) ? situationRValues.get(index) : previous_value1.getValue();
//                    })
//                    .collect(Collectors.toList());


            ValueHolder previous_value12 = new ValueHolder(null); // Initialize to null
            List<Double> orderedSituationFValues = new ArrayList<>();

            orderedDates.stream().forEach(
                    date -> {
                        boolean found = false; // Flag to check if situation is found for the given date


                        for (Situation s: marche.getSituations()){
                            if(date.equals(s.getDatesituation()) && s.isValidated() ){
                                orderedSituationFValues.add(s.getAvancement_fenancier());
                                previous_value12.setValue(s.getAvancement_fenancier());
                                found = true;
                                break;
                            }
                        }

                        // If situation is not found for the given date, add null
                        if (!found) {
                            orderedSituationFValues.add(previous_value12.getValue());
                        }
                    }
            );

//            List<Double> orderedSituationFValues = orderedDates.stream()
//                    .map(date -> {
//                        int index = mesDates.indexOf(date);
//
//                        if(index != -1 && !situationFValues.isEmpty()  && situationFValues.size()>(index)){
//                            previous_value12.setValue(situationFValues.get(index));
//                        }
//                        return index != -1 && !situationFValues.isEmpty()  && situationFValues.size()>(index)  ? situationFValues.get(index) : previous_value12.getValue();
//                    })
//                    .collect(Collectors.toList());

            // Order the lists corresponding to Situation_r values by date

            ValueHolder previous_value13 = new ValueHolder(null); // Initialize to null
            List<Double> orderedSituationRFValues = new ArrayList<>();

            orderedDates.stream().forEach(
                    date -> {
                        boolean found = false; // Flag to check if situation is found for the given date

                        for (Situation_r s: marche.getSituations_r()){
                            if(date.equals(s.getDatesituation())){
                                orderedSituationRFValues.add(s.getAvancement_fenancier());
                                previous_value13.setValue(s.getAvancement_fenancier());
                                found = true;
                                break;
                            }
                        }

                        // If situation is not found for the given date, add null
                        if (!found) {
                            orderedSituationRFValues.add(previous_value13.getValue());
                        }
                    }
            );

//            List<Double> orderedSituationRFValues = orderedDates.stream()
//                    .map(date -> {
//                        int index = mesDates.indexOf(date);
//
//                        if(index != -1 && !situationRFValues.isEmpty()  && situationRFValues.size()>(index)){
//                            previous_value13.setValue(situationRFValues.get(index));
//                        }
//                        return index != -1 && !situationRFValues.isEmpty()  && situationRFValues.size()>(index)  ? situationRFValues.get(index) : previous_value13.getValue();
//                    })
//                    .collect(Collectors.toList());


            // Now, you have three lists ordered by date






            SituationGraphDto situationGraphDto = SituationGraphDto.builder()
                    .orderedSituationRFValues(orderedSituationRFValues)
                    .orderedSituationRValues(orderedSituationRValues)
                    .orderedSituationValues(orderedSituationValues)
                    .orderedSituationFValues(orderedSituationFValues)
                    .formatedDates(formatedDates)
                    .build();

            return situationGraphDto;

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }




    public BarDto getavancementDesIndicateursP(Long marcheID) {

        try {
            Marche marche = marche_repo.findById(marcheID).orElseThrow(() -> new RuntimeException("Marche not found"));


            BarDto barDto = BarDto.builder().situation_r(getLastOrFutureSituationRForMarche(marche)).situation(getLastOrFutureSituationForMarche(marche)).build();



            return barDto;

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }








//    public MarcheDto getStuationMarche(Long marcheID){
//        Marche marche = this.marche_repo.findById(marcheID).get();
//
//
//       Situation st = this.getLastSituationForMarche(marche);
//
//
//        Double avReel = 0D;
//        Double avPgt = 0D;
//
//
//            Double avReelval = 0D;
//            Double avPgtval = 0D;
//            Double montant = 0D;
//            if(st.getDatesituation()!= null){
//                for ( SituationDetail situationDetail: st.getSituationdetail()){
//                    if(situationDetail.getPrix().getPu()!=null &&  situationDetail.getQteprecu() !=null && situationDetail.getQtereacu()!= null){
//                        avReelval = avReelval+ situationDetail.getPrix().getPu() * situationDetail.getQteprecu();
//                        avPgtval = avPgtval+ situationDetail.getPrix().getPu() * situationDetail.getQtereacu();
//                        montant = montant+ situationDetail.getPrix().getMontant();
//                    }
//                }
//                avReel =  (double) Math.round(100* avReelval/montant);
//                avPgt = (double) Math.round(100*avReelval/montant);
//
//            }
//        MarcheDto marcheDto = MarcheDto.builder().delai(marche.getDelai()).id_marche(marche.getId_marche()).dateOverturePlit(marche.getDateOverturePlit()).numero_marche(marche.getNumero_marche()).delegation(marche.getDelegation()).estimationao(marche.getEstimationao()).avPgt(avPgt).avReel(avReel).situation(st).build();
//
//        return marcheDto;
//
//    }




//    public NiveauDto calculateForNiveau(Niveau n) {
//
//
//        Double avReel = 0D;
//        Double avPgt = 0D;
//        Double avReelval = 0D;
//        Double avPgtval = 0D;
//        Double montant = 0D;
//        List<ProjectDTO> projectDTOS = new ArrayList<>();
//        for(Projet p: n.getProjets()){
//
//            ProjectDTO projectDTO = this.calculate(p);
//            avReelval =avReelval + projectDTO.getReel();
//            avPgtval = avPgtval + projectDTO.getPgt();
//            montant =montant + projectDTO.getMontant();
//
//            projectDTO.setMyProject(p);
//            projectDTOS.add(projectDTO);
//        }
//
//
//        avReel = ( (double) Math.round(100* avReelval/montant));
//        avPgt =  ((double) Math.round(100*avReelval/montant));
//
//        return NiveauDto.builder().projectDTOS(projectDTOS).niveau(n).avReel(avReel).avPgt(avPgt).build();
//
//    }

//    public ProjectDTO calculate(Projet p) {
//
//
//
//
//        Double avReel = 0D;
//        Double avPgt = 0D;
//        Double avReelval = 0D;
//        Double avPgtval = 0D;
//        Double montant = 0D;
//        List<LotDto> lotDtos = new ArrayList<>();
//        for(Lot l:p.getLots()){
//
//
//
//            List<Long> marchesId = new ArrayList<>();
//
//            Lot lot = this.lotService.findLotById(l.getId()).get();
//            for(Marche m: lot.getMarches()){
//                marchesId.add(m.getId_marche());
//
//            }
//
//            LotDto lotDto = this.getStuationLot(marchesId);
//            avReelval =avReelval + lotDto.getReel();
//            avPgtval = avPgtval + lotDto.getPgt();
//            montant =montant + lotDto.getMontant();
//            lotDto.setMyLot(lot);
//
//            lotDtos.add(lotDto);
//        }
//
//        avReel = ( (double) Math.round(100* avReelval/montant));
//        avPgt =  ((double) Math.round(100*avReelval/montant));
//
//        return ProjectDTO.builder().myProject(p).avReel(avReel).Reel(avReelval).Pgt(avPgtval).montant(montant).avPgt(avPgt).lotDtos(lotDtos).build();
//
//
//
//    }


//    public LotDto getStuationLot(List<Long> marcheIDS){
//        List <Marche> marches = this.marche_repo.findAllById(marcheIDS);
//
//
//        List<Situation> situations = new ArrayList<>();
//        for(Marche m: marches){
//            situations.add(this.getLastSituationForMarche(m)) ;
//        }
//
//
////        Double avReel = 0D;
////        Double avPgt = 0D;
//
//        Double avReel = 0D;
//        Double avPgt = 0D;
//
//        Double avReelval = 0D;
//        Double avPgtval = 0D;
//        Double montant = 0D;
//
//        for(Situation st : situations){
//            if(st.getDatesituation()!= null){
//                for ( SituationDetail situationDetail: st.getSituationdetail()){
//                    if(situationDetail.getPrix().getPu()!=null &&  situationDetail.getQteprecu() !=null && situationDetail.getQtereacu()!= null){
//                        avReelval = avReelval+ situationDetail.getPrix().getPu() * situationDetail.getQteprecu();
//                        avPgtval = avPgtval+ situationDetail.getPrix().getPu() * situationDetail.getQtereacu();
//                        montant = montant+ situationDetail.getPrix().getMontant();
//                    }
//                }
//            }
//        }
//        avReel = ( (double) Math.round(100* avReelval/montant));
//        avPgt =  ((double) Math.round(100*avReelval/montant));
//
//        List<MarcheDto> marcheDto = new ArrayList<>();
//
//        for(long id:marcheIDS ){
//            marcheDto.add(this.getStuationMarche(id));
//
//        }
//
//
//        LotDto lotDto = LotDto.builder().avReel(avReel).avPgt(avPgt).Reel(avReelval).Pgt(avPgtval).montant(montant).marcheDtoList(marcheDto).build();
//
//        return lotDto;
//
//    }



    public Marche saveMarche(Marche marche) {
        return marche_repo.save(marche);
    }



    public String getformatedDate(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if(d != null){
            return  formatter.format(d);

        }
        return "-----";
    }



//    public SituationsSummaryDTO getEvolutionAvancement(Long marcheID) {
//        Marche marche = this.marche_repo.findById(marcheID).get();
//
//
//        List<Situation> sortedSituations = marche.getSituations().stream()
//                .sorted(Comparator.comparing(Situation::getId_situation))
//                .collect(Collectors.toList());
//
//        List<Double> avReel = new ArrayList<>();
//        List<Double> avPgt = new ArrayList<>();
//        List<String> Dates = new ArrayList<>();
//
//        for(Situation st : sortedSituations){
//            Double avReelval = 0D;
//            Double avPgtval = 0D;
//            Double montant = 0D;
//            if(st.getDatesituation()!= null){
//                for ( SituationDetail situationDetail: st.getSituationdetail()){
//                    if(situationDetail.getPrix().getPu()!=null &&  situationDetail.getQteprecu() !=null && situationDetail.getQtereacu()!= null){
//                        avReelval = avReelval+ situationDetail.getPrix().getPu() * situationDetail.getQteprecu();
//                        avPgtval = avPgtval+ situationDetail.getPrix().getPu() * situationDetail.getQtereacu();
//                        montant = montant+ situationDetail.getPrix().getMontant();
//
//
//
//
//
//
//
//
//                    }
//
//                }
//
//              ;
//                avReel.add( (double) Math.round(100* avReelval/montant));
//                avPgt.add((double) Math.round(100*avPgtval/montant));
//                Dates.add(getformatedDate(st.getDatesituation()));
//
//            }
//        }
//
//
//        SituationsSummaryDTO summaryDTO = new SituationsSummaryDTO(avReel, avPgt, Dates);
//        return summaryDTO;
//
//    }


      public RadioDTO getRadar(Long marcheID){

          Marche marche = this.marche_repo.findById(marcheID).get();

//          Situation lastSituation = this.getLastSituationForMarche(marche);

          List<Double> avReel = new ArrayList<>();
          List<Double> avPgt = new ArrayList<>();
          List<String> prix = new ArrayList<>();



//          for(SituationDetail std : lastSituation.getSituationdetail()){
//              if(std.getPrix().getQte() != null && std.getPrix().getQte() !=0 && std.getQteprecu() != null &&  std.getQtereacu()!= null){
//                  avReel.add((double) Math.round(100*std.getQteprecu()/std.getPrix().getQte()));
//                  avPgt.add((double) Math.round(100*std.getQtereacu()/std.getPrix().getQte()));
//                  prix.add(std.getPrix().getPrix());
//              }
//
//          }


          RadioDTO radioDTO = new RadioDTO(avReel, avPgt, prix);
          return radioDTO;

    }



}



class ValueHolder {
    Double value;

    public ValueHolder(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
package com.example.demo.Dao;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Projet_RepoTest {
//@Autowired
//    private Projet_Repo projetRepository;
//
//    @Autowired
//    private Province_Repo provinceRepo;
//
//    @Autowired
//    public Service service;
//
//
//    @Autowired
//    private Lot_Repo lotRepo;
//
//    @Autowired
//    public Marche_Repo marche_repo;
//
//
//    @Autowired
//    public MarcheService marcheService;
//
//
//    @Test
//    public void gg(){
//
//    }
//    @Test
//public void get(){
//    this.projetRepository.findAll().forEach(System.out::println);
//
//
//
//
//        }
//
//    @Test
//    public void save1(){
//        Set<Section> sections = new HashSet<>();
//        sections.add(Section.builder().province(provinceRepo.findAll().get(0)).build());
//
//
//       Projet projet =  this.projetRepository.findAll().get(0);
//       projet.setSections(sections);
//
//        projetRepository.save(projet);
//    }
//
//    @Test
//    public void getgeomp(){
//
//        Prix prix = Prix.builder()
//                .numprix(1)
//                .prix("100")
//                .unite("m2")
//                .qte(10.0)
//                .pu(100.0)
//                .montant(1000.0)
//                .build();
//
//        // Create SituationDetail entity
//        SituationDetail situationDetail = SituationDetail.builder()
//                .qteprecu(5.0)
//                .qtereacu(5.0)
//                .montapgt(500.0)
//                .montreal(500.0)
//                .avancpgt(50.0)
//                .avanreal(50.0)
//                .ecart(0.0)
//                .qtecalcu(5.0)
//                .pucalcul(100.0)
//                .prix(prix)
//                .build();
//
//        // Create Situation entity
//        Situation situation = Situation.builder()
//                .datesituation(new Date())
//                .obervation("Observation")
//                .avancementPGT(50.0)
//                .avancementReel(50.0)
//                .situationdetail(Arrays.asList(situationDetail))
//                .build();
//
//        // Create Marche entity
//        Marche marche = Marche.builder()
//                .numero_marche("12345")
//                .delai(30)
//                .objet("Construction")
//                .titulaire("Company A")
//                .montant(100000.0)
//                .os_commencement(new Date())
//                .dateOverturePlit(new Date())
//                .n_appel_offre("AO-123")
//                .prixes(Arrays.asList(prix))
//                .build();
//
//        prix.setMarche(marche);
//
//
//        // Save Marche entity with all elements
//        Marche savedMarche = this.marche_repo.save(marche);
//
//
//       Lot l = lotRepo.findById(138L).get();
//       l.setMarche(savedMarche);
//
//       lotRepo.save(l);
//
//    }
//
//
//
//
//    public String getformatedDate(Date d){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        if(d != null){
//            return  formatter.format(d);
//
//        }
//        return "-----";
//    }
//
//    public ResponseEntity<Situation> getLastSituationForMarche(@RequestBody Marche marche) {
//        Situation lastSituation = marcheService.getLastSituationForMarche(marche);
//        if (lastSituation == null) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(lastSituation);
//    }
//
//    @Test
//    public void help(){
//
//        Marche marche = marche_repo.findById(179L).get();
//        Situation lastSituation = marcheService.getLastSituationForMarche(marche);
//
//        List<Double> avReel = new ArrayList<>();
//        List<Double> avPgt = new ArrayList<>();
//        List<String> prix = new ArrayList<>();
//
//
//
//        for(SituationDetail std : lastSituation.getSituationdetail()){
//            if(std.getPrix().getQte() != null && std.getPrix().getQte() !=0)
//                avReel.add((double) Math.round(std.getQteprecu()/std.getPrix().getQte()));
//                avPgt.add((double) Math.round(std.getQtereacu()/std.getPrix().getQte()));
//                prix.add(std.getPrix().getPrix());
//        }
//
//        System.out.println(avReel);
//        System.out.println(avPgt);
//        System.out.println(prix);
//
//
//
//
//    }


}












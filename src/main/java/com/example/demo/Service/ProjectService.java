package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Dtos.Response;
import com.example.demo.Entities.*;
import com.example.demo.Enums.ProgrammeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

public class ProjectService {

    @Autowired
    private Projet_Repo projetRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Section_Repo sectionRepo;

    @Autowired
    private Situation_Repo situationRepo;

    @Autowired
    private NotificationService notificationService;

    public Optional<Projet> findprojetById(Long id){
        return this.projetRepo.findById(id);
    }

    public List<Projet> getProjectsByProgramme(String programme){

        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the role of the authenticated user
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();


        if ("Role_Admin".equals(userRole)){
            return this.projetRepo.findAllByProgrammeType(ProgrammeType.valueOf(programme));

        }else {
            String username = authentication.getName();

            MyUser myUser= userRepository.findByUsername(username);

            List<Projet> projects =  this.projetRepo.findAllByProgrammeTypeAndProvinceId(ProgrammeType.valueOf(programme),myUser.getProvince().getId_province());

            return projects;

        }

    }



    public List<Projet> findInvalidatedProjects(){
        return this.projetRepo.findInvalidatedProjects();
    }


    public ResponseEntity<?> validateSt(Situation situation,String content,Long sec_id){

        try {
            situation.setValidated(true);
            this.situationRepo.save(situation);

            Section section =sectionRepo.findById(sec_id).orElseThrow(()->new RuntimeException("section not found"));

            List<MyUser> users = userRepository.findAllByProvince_Id_province(section.getProvince().getId_province()).orElseThrow(()->new RuntimeException("province not found"));

            if(!users.isEmpty()){


                for(MyUser u:users){


                    notificationService.createNotificationForUser(u,situation,content,section);
                }
            }

         return ResponseEntity.status(200).body(Response.builder().message("well done").build());
        }catch (Exception e){
            return ResponseEntity.status(500).body(Response.builder().message("enable to save the situation").build());

        }



    }

  public ResponseEntity<?> invalidateSt(Situation situation,String content,Long sec_id){

        try {

            Section section =sectionRepo.findById(sec_id).orElseThrow(()->new RuntimeException("section not found"));

            List<MyUser> users = userRepository.findAllByProvince_Id_province(section.getProvince().getId_province()).orElseThrow(()->new RuntimeException("province not found"));

            if(!users.isEmpty()){


                for(MyUser u:users){


                    notificationService.createNotificationForUserinvalidate(u,situation,content,section);
                }
            }

            this.situationRepo.delete(situation);

         return ResponseEntity.status(200).body(Response.builder().message("well done").build());
        }catch (Exception e){
            return ResponseEntity.status(500).body(Response.builder().message("enable to save the situation").build());

        }



    }


}

package com.example.demo.Controllers;

import com.example.demo.Dtos.ValidateSituationDto;
import com.example.demo.Entities.Situation;
import com.example.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "project")
public class ProjectController {
    @Autowired
    public ProjectService projectService;


    @GetMapping(value = "/getProjectsByProgramme/{programme}")
    public ResponseEntity<?> getProvinecs(@PathVariable("programme") String programme) {
        return new ResponseEntity<>(this.projectService.getProjectsByProgramme(programme), HttpStatus.OK);
    }


   @GetMapping(value = "/findInvalidatedProjects")
    public ResponseEntity<?> findInvalidatedProjects() {
        return new ResponseEntity<>(this.projectService.findInvalidatedProjects(), HttpStatus.OK);
    }


    @PostMapping(value = "/validateSt")
    public ResponseEntity<?> validateSt(@RequestBody ValidateSituationDto situation) {
        return new ResponseEntity<>(this.projectService.validateSt(situation.getSituation(),situation.getContent(),situation.getSec_id()), HttpStatus.OK);
    }


    @PostMapping(value = "/invalidateSt")
    public ResponseEntity<?> invalidateSt(@RequestBody ValidateSituationDto situation) {
        return new ResponseEntity<>(this.projectService.invalidateSt(situation.getSituation(),situation.getContent(),situation.getSec_id()), HttpStatus.OK);
    }


}

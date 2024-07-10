package com.example.demo.Controllers;

import com.example.demo.Dao.CommuneRepository;
import com.example.demo.Entities.Commune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/communes")
public class CommuneController {
    private final CommuneRepository communeRepository;

    @Autowired
    public CommuneController(CommuneRepository communeRepository) {
        this.communeRepository = communeRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Commune>> getAllCommunes() {
        List<Commune> communes = communeRepository.findAll();
        return new ResponseEntity<>(communes, HttpStatus.OK);
    }
}

package com.example.demo.Controllers;

import com.example.demo.Dtos.PrestationAcquisitionDto;
import com.example.demo.Entities.PrestationAcquisition;
import com.example.demo.Service.PrestationAquiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestationAcquisition")
public class PrestationAcquisitionController {

    private final PrestationAquiService prestationAcquisitionService;

    @Autowired
    public PrestationAcquisitionController(PrestationAquiService prestationAcquisitionService) {
        this.prestationAcquisitionService = prestationAcquisitionService;
    }

    @PostMapping
    public ResponseEntity<PrestationAcquisition> addPrestationAcquisition(@RequestBody PrestationAcquisitionDto prestationAcquisition) {
        PrestationAcquisition createdPrestationAcquisition = prestationAcquisitionService.addPrestationAcquisition(prestationAcquisition);
        return new ResponseEntity<>(createdPrestationAcquisition, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestationAcquisition> updatePrestationAcquisition(@PathVariable Long id, @RequestBody PrestationAcquisition prestationAcquisition) {
        PrestationAcquisition updatedPrestationAcquisition = prestationAcquisitionService.editPrestationAcquisition(id,prestationAcquisition);
        return new ResponseEntity<>(updatedPrestationAcquisition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestationAcquisition(@PathVariable Long id) {
        prestationAcquisitionService.deletePrestationAcquisition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestationAcquisition> getPrestationAcquisitionById(@PathVariable Long id) {
        PrestationAcquisition prestationAcquisition = prestationAcquisitionService.getPrestationAcquisitionById(id).get();
        return new ResponseEntity<>(prestationAcquisition, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PrestationAcquisition>> getAllPrestationAcquisitions() {
        List<PrestationAcquisition> prestationAcquisitions = prestationAcquisitionService.getAllPrestationAcquisitions();
        return new ResponseEntity<>(prestationAcquisitions, HttpStatus.OK);
    }
}
package com.example.Gestion_Recrutement.rest.controller;


import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Entretien;
import com.example.Gestion_Recrutement.service.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entretien")
public class EntretienController {

    @Autowired
    private final EntretienService entretienService;
    public EntretienController(EntretienService entretienService){
        this.entretienService=entretienService;
    }


    @PostMapping("/")
    public ResponseEntity<Candidat> addEntretien(@RequestBody Entretien entretien) throws ParseException {
        Entretien entretien1 = entretienService.addEntretien(entretien);
        return new ResponseEntity(entretien1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Entretien>> findEntretiensByRH (@PathVariable("id") Long id) {
        List<Entretien> entretien = entretienService.findEntretiensByRH(id);
        return new ResponseEntity(entretien, HttpStatus.OK);
    }



    @GetMapping("/single/{id}")
    public ResponseEntity<Entretien> findEntretiensById (@PathVariable("id") Long id) {
        Optional<Entretien> entretien = entretienService.findEntretiensById(id);
        return new ResponseEntity(entretien, HttpStatus.OK);
    }

    @GetMapping("/candidat/{id}")
    public ResponseEntity<List<Entretien>> findEntretiensByCandidatId (@PathVariable("id") Long id) {
        List<Entretien> entretien = entretienService.findEntretiensByCandidatId(id);
        return new ResponseEntity(entretien, HttpStatus.OK);
    }

    @GetMapping("/Completed/{id}")
    public void UpdateEtatToCompleted (@PathVariable("id") Long id) {
         entretienService.UpdateEtatToCompleted(id);
    }

    @GetMapping("/Refused/{id}")
    public void UpdateEtatToRefused (@PathVariable("id") Long id) {
         entretienService.UpdateEtatToRefused(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteEntretien (@PathVariable("id") Long id) {
         entretienService.deleteEntretien(id);
    }
}

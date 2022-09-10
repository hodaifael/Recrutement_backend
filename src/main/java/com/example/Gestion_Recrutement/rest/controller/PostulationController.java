package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Offre;
import com.example.Gestion_Recrutement.entity.Postulation;
import com.example.Gestion_Recrutement.service.CandidatService;
import com.example.Gestion_Recrutement.service.PostulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/postulation")
public class PostulationController {
    @Autowired
    private final PostulationService postulationService ;
    public PostulationController(PostulationService postulationService){
        this.postulationService=postulationService;
    }


    @PostMapping("/")
    public ResponseEntity<Postulation> addPostulation(@RequestBody Postulation postulation) throws ParseException {
        Postulation postulation1 = postulationService.addPostulation(postulation);
        return new ResponseEntity(postulation1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Postulation>> getPostulation (@PathVariable("id") Long id) {
        List<Postulation> postulations = postulationService.getPostulation(id);
        return new ResponseEntity(postulations, HttpStatus.OK);
    }
}

package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import com.example.Gestion_Recrutement.service.CandidatService;
import com.example.Gestion_Recrutement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }



    @PostMapping("/all/")
    public ResponseEntity<List<Utilisateur>> findListUtilisateur(@RequestParam("age") String age, @RequestParam("field_study") String field_study, @RequestParam("degree") String degree, @RequestParam("field_job") String field_job) throws ParseException {
        List<Utilisateur> utilisateur = utilisateurService.findListUtilisateur(age,field_study,degree,field_job);
        return new ResponseEntity(utilisateur, HttpStatus.CREATED);
    }

}

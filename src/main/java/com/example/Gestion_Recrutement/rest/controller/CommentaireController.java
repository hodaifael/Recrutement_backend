package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Commentaire;
import com.example.Gestion_Recrutement.entity.Entretien;
import com.example.Gestion_Recrutement.service.CommentaireService;
import com.example.Gestion_Recrutement.service.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/commentaire")
public class CommentaireController {

    @Autowired
    private final CommentaireService commentaireService;
    public CommentaireController(CommentaireService commentaireService){
        this.commentaireService=commentaireService;
    }


    @PostMapping("/")
    public ResponseEntity<Commentaire> addCommentaire(@RequestBody Commentaire commentaire) throws ParseException {
        Commentaire commentaire1= commentaireService.addCommentaire(commentaire);
        return new ResponseEntity(commentaire1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Commentaire>> findCommentaireByEntretien (@PathVariable("id") Long id) {
        List<Commentaire> commentaire = commentaireService.findCommentaireByEntretien(id);
        return new ResponseEntity(commentaire, HttpStatus.OK);
    }
}

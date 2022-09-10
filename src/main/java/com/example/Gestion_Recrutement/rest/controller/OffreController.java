package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.*;
import com.example.Gestion_Recrutement.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/offre")
public class OffreController {

    @Autowired
    private final OffreService offreService;
    public OffreController(OffreService offreService){
        this.offreService=offreService;
    }

    @PostMapping("/")
    public ResponseEntity<Integer> addProfile(@RequestBody Offre offre) throws ParseException, MessagingException, UnsupportedEncodingException {
        int nbrOfCandidat= offreService.addOffre(offre);
        return new ResponseEntity(nbrOfCandidat, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Offre>> findOffreByAll () {
        List<Offre> offres = offreService.findOffreByAll();
        return new ResponseEntity<>(offres, HttpStatus.OK);
    }

    @PostMapping("/filter/")
    public ResponseEntity<List<Offre>> findOffreByFilter (@RequestBody Offre offre) {
        List<Offre> offres = offreService.findOffreByFilter(offre);
        return new ResponseEntity<>(offres, HttpStatus.OK);
    }

    @PostMapping("/like/")
    public ResponseEntity<OffreLike> like(@RequestBody OffreLike like) {
        OffreLike like1= offreService.like(like);
        return new ResponseEntity(like1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Offre>> getOffres (@PathVariable("id") Long id) {
        List<Offre> offre = offreService.getOffres(id);
        return new ResponseEntity(offre, HttpStatus.OK);
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<Offre> getOffreById (@PathVariable("id") Long id) {
        Offre offre = offreService.getOffreById(id);
        return new ResponseEntity(offre, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public void DeleteOffre (@PathVariable("id") Long id) {
        offreService.DeleteOffre(id);
    }

}

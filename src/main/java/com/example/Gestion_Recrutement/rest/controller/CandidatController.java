package com.example.Gestion_Recrutement.rest.controller;


import com.example.Gestion_Recrutement.entity.*;
import com.example.Gestion_Recrutement.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/candidat")
public class CandidatController {

    @Autowired
    private final CandidatService candidatService;
    public CandidatController(CandidatService candidatService){
        this.candidatService=candidatService;
    }

    @PostMapping("/")
    public ResponseEntity<Candidat> updateProfile(@RequestBody Candidat candidat) throws ParseException {
        Candidat candidat1 =candidatService.updateProfile(candidat);
        return new ResponseEntity<Candidat>(candidat, HttpStatus.OK);
    }

    @PostMapping("/filter/")
    public ResponseEntity<List<Candidat>> findListCondidat (@RequestBody Candidat candidat) {
        List<Candidat> candidats = candidatService.findListCondidat(candidat);
        return new ResponseEntity<>(candidats, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Candidat>> findListCondidatall () {
        List<Candidat> candidats = candidatService.findListCondidatall();
        return new ResponseEntity<>(candidats, HttpStatus.OK);
    }




    @PostMapping("/image/")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        candidatService.save(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> findCandidatById (@PathVariable("id") Long id) {
        Candidat candidat = candidatService.findCandidatById(id);
        return new ResponseEntity(candidat, HttpStatus.OK);
    }
}

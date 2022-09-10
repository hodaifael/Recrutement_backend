package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.Admin;
import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.ResponsableRH;
import com.example.Gestion_Recrutement.entity.UploadResponseMessage;
import com.example.Gestion_Recrutement.service.CandidatService;
import com.example.Gestion_Recrutement.service.ResponsableRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
@RestController
@RequestMapping("/responsableRH")
public class ResponsableRHController {

    @Autowired
    private final ResponsableRHService responsableRHService;
    public ResponsableRHController(ResponsableRHService responsableRHService){
        this.responsableRHService=responsableRHService;
    }

    @PostMapping("/compte/")
    public ResponseEntity<String> createCompte(@RequestBody ResponsableRH responsableRH) throws ParseException {
        responsableRHService.saveCompte(responsableRH);
        return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponsableRH> updateProfile(@RequestBody ResponsableRH responsableRH) throws ParseException {
        ResponsableRH responsableRH1 =responsableRHService.updateProfile(responsableRH);
        return new ResponseEntity<ResponsableRH>(responsableRH1, HttpStatus.OK);
    }

    @PostMapping("/image/")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        responsableRHService.save(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponsableRH> findUtilisateurByid (@PathVariable("id") Long id) {
        ResponsableRH responsableRH = responsableRHService.findResponsableRHByID(id);
        return new ResponseEntity(responsableRH, HttpStatus.OK);
    }
}


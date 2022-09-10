package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.Experience;
import com.example.Gestion_Recrutement.entity.Formation;
import com.example.Gestion_Recrutement.service.ExperienceService;
import com.example.Gestion_Recrutement.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private final ExperienceService experienceService;
    public ExperienceController(ExperienceService experienceService){
        this.experienceService=experienceService;
    }

    @PostMapping("/")
    public ResponseEntity<Experience> addFormation(@RequestBody Experience experience) throws ParseException {
        Experience experience1 = experienceService.addExperience(experience);
        return new ResponseEntity(experience1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Experience>> findUtilisateurByid (@PathVariable("id") Long id) {
        List<Experience> experience = experienceService.findExperienceByCondidat(id);
        return new ResponseEntity(experience, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public void deleteExperience (@PathVariable("id") Long id) {
        experienceService.deleteExperience(id);
    }
}

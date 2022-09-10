package com.example.Gestion_Recrutement.rest.controller;


import com.example.Gestion_Recrutement.entity.Formation;
import com.example.Gestion_Recrutement.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {

    @Autowired
    private final FormationService formationService;
    public FormationController(FormationService formationService){
        this.formationService=formationService;
    }

    @PostMapping("/")
    public ResponseEntity<Formation> addFormation(@RequestBody Formation formation) throws ParseException {
        Formation formation1 = formationService.addFormation(formation);
        return new ResponseEntity(formation1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Formation>> findFormationByCondidat (@PathVariable("id") Long id) {
        List<Formation> formation = formationService.findFormationByCondidat(id);
        return new ResponseEntity(formation, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public void deleteFormation (@PathVariable("id") Long id) {
        formationService.deleteFormation(id);
    }
}

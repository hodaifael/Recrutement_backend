package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Company;
import com.example.Gestion_Recrutement.entity.UploadResponseMessage;
import com.example.Gestion_Recrutement.service.CandidatService;
import com.example.Gestion_Recrutement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }


    @PostMapping("/")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) throws ParseException {
        Company company1 = companyService.addCompany(company);
        return new ResponseEntity(company1, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<Company>> findUtilisateurByid () {
        List<Company> companies = companyService.findAll();
        return new ResponseEntity(companies, HttpStatus.OK);
    }

    @PostMapping("/image/")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        companyService.save(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
}

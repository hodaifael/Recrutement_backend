package com.example.Gestion_Recrutement.rest.controller;

import com.example.Gestion_Recrutement.entity.*;
import com.example.Gestion_Recrutement.service.AdminService;
import com.example.Gestion_Recrutement.service.ResponsableRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;


    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }


    @PostMapping("/")
        public ResponseEntity<Admin> updateProfile(@RequestBody Admin admin) throws ParseException {
        Admin admin1 =adminService.updateProfile(admin);
        return new ResponseEntity<Admin>(admin1, HttpStatus.OK);
    }


    @PostMapping("/image/")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        adminService.save(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }

    @PostMapping("/compte/")
    public ResponseEntity<String> createCompte(@RequestBody Admin admin) throws ParseException {
        adminService.saveCompte(admin);
        return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Utilisateur>> findListCompte () {
        List<Utilisateur> comptes = adminService.findListCompte();
        return new ResponseEntity(comptes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> findUtilisateurByid (@PathVariable("id") Long id) {
        Admin admin = adminService.findAdminById(id);
        return new ResponseEntity(admin, HttpStatus.OK);
    }
}

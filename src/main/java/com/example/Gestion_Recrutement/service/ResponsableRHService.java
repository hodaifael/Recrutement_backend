package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.ExceptionAndFilters.FileUploadException;
import com.example.Gestion_Recrutement.entity.Admin;
import com.example.Gestion_Recrutement.entity.ResponsableRH;
import com.example.Gestion_Recrutement.repository.ResponsableRHRepository;
import com.example.Gestion_Recrutement.repository.UtilisateurRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;


@Service
@Transactional
public class ResponsableRHService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private final ResponsableRHRepository rhRepository;
    private final CompanyService companyService;
    private final UtilisateurRepository utilisateurRepository;

    public ResponsableRHService(ResponsableRHRepository rhRepository, CompanyService companyService, UtilisateurRepository utilisateurRepository){
        this.rhRepository=rhRepository;
        this.companyService=companyService;
        this.utilisateurRepository = utilisateurRepository;
    }

    public ResponsableRH updateProfile(ResponsableRH responsableRH) throws ParseException {
        String password=passwordEncoder.encode(responsableRH.getUserPass());
        responsableRH.setUserPass(password);
        responsableRH.setTime(LocalTime.now());
        setDate(responsableRH);
        ResponsableRH responsableRH1=utilisateurRepository.save(responsableRH);
        return responsableRH1;
    }


    public ResponsableRH saveCompte(ResponsableRH responsableRH) throws ParseException {
        if (utilisateurRepository.findByUserEmail(responsableRH.getUserEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        String password=passwordEncoder.encode(responsableRH.getUserPass());
        responsableRH.setUserPass(password);
        String randomCode = RandomString.make(64);
        responsableRH.setVerificationCode(randomCode);
        responsableRH.setEnabled(false);
        responsableRH.setTime(LocalTime.now());
        setDate(responsableRH);
        utilisateurRepository.save(responsableRH);

        return responsableRH;
    }

    public void setDate(ResponsableRH responsableRH)throws ParseException{
        String pattern = "MM-dd-yyyy";
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);
        String stringDate= DateFor.format(date);
        Date date1 = DateFor.parse(stringDate);
        responsableRH.setDate(date1);
    }

    public void save(MultipartFile file, Long id) throws FileUploadException {
        try {
            String pattern = "MMddyyyyHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String filename=date+file.getOriginalFilename();

            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(filename);
            rhRepository.updateimage(filename,id);
            if (resolve.toFile()
                    .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
        }

    }

    public ResponsableRH findResponsableRHByID (Long id){
        ResponsableRH responsableRH= rhRepository.findResponsableRHByID(id);
        return responsableRH;
    }


}

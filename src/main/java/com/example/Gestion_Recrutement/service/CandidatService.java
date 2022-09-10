package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.ExceptionAndFilters.FileUploadException;
import com.example.Gestion_Recrutement.entity.Admin;
import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Offre;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import com.example.Gestion_Recrutement.repository.CandidatRepository;
import com.example.Gestion_Recrutement.repository.UtilisateurRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class CandidatService  {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final CandidatRepository condidatRepository;
    private final UtilisateurRepository utilisateurRepository;



    public CandidatService(CandidatRepository condidatRepository, UtilisateurRepository utilisateurRepository){
        this.condidatRepository=condidatRepository;
        this.utilisateurRepository=utilisateurRepository;
    }

    public Candidat updateProfile(Candidat candidat) throws ParseException {
        String password=passwordEncoder.encode(candidat.getUserPass());
        candidat.setUserPass(password);
        candidat.setTime(LocalTime.now());
        setDate(candidat);
        Candidat candidat1=utilisateurRepository.save(candidat);
        return candidat1;
    }



    public List<Candidat> findListCondidat(Candidat candidat){

        return condidatRepository.findListCondidat(candidat.getSpecialite(),candidat.getNiveau_d_etude(),candidat.getNiveau_d_experience());
    }

    public List<Candidat> findListCondidatall(){
        return condidatRepository.findAll();
    }



    public Candidat findCandidatById (Long id){
        Candidat candidat= condidatRepository.findCandidatById(id);
        return candidat;
    }

    public void save(MultipartFile file, Long id) throws FileUploadException {
        try {
            String pattern = "MMddyyyyHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String filename=date+file.getOriginalFilename();

            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(filename);
            condidatRepository.updateimage(filename,id);
            if (resolve.toFile()
                    .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
        }

    }

    public void setDate(Candidat candidat)throws ParseException{
        String pattern = "MM-dd-yyyy";
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);
        String stringDate= DateFor.format(date);
        Date date1 = DateFor.parse(stringDate);
        candidat.setDate(date1);
    }
}

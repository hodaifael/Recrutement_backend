package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.ExceptionAndFilters.FileUploadException;
import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Utilisateur;
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
import java.util.Optional;


@Service
@Transactional
public class UtilisateurService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository=utilisateurRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUserEmail(username).get();
        List<GrantedAuthority> simpleGrantedAuthorities= Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole()));
        List<GrantedAuthority> grantedAuthorities = simpleGrantedAuthorities;

        return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);
    }

    public Utilisateur getUser(String username) {
        Utilisateur user = utilisateurRepository.findByUserEmail(username).get();
        return user;
    }

    public List<Utilisateur> findListUtilisateur( String age, String field_study, String degree, String field_job) throws ParseException {
        return utilisateurRepository.findListUtilisateur(age, field_study, degree, field_job);
    }


    public Optional<Utilisateur> findUserById(Long id){
        return utilisateurRepository.findById(id);
    }
    public Boolean verifyCompte(String code) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.verifyCompte(code);
        if(utilisateur.isPresent() ){
            if(utilisateur.get().getEnabled() == false){
                System.out.println("hello");
                utilisateurRepository.UpdateEnabled(utilisateur.get().getId());
            }
            return true;
        }
        return false;

    }

    public Candidat addCondidat(Candidat candidat) throws ParseException {
        if (utilisateurRepository.findByUserEmail(candidat.getUserEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String password=passwordEncoder.encode(candidat.getUserPass());
        candidat.setUserPass(password);
        String randomCode = RandomString.make(64);
        candidat.setVerificationCode(randomCode);
        candidat.setEnabled(false);
        candidat.setTime(LocalTime.now());
        setDate(candidat);
        Candidat candidat1=utilisateurRepository.save(candidat);
        try {
            sendVerificationEmail(candidat,"http://localhost:4200");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return candidat1;
    }
    public void setDate(Candidat candidat)throws ParseException{
        String pattern = "MM-dd-yyyy";
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);
        String stringDate= DateFor.format(date);
        Date date1 = DateFor.parse(stringDate);
        candidat.setDate(date1);
    }

    public void sendVerificationEmail(Candidat candidat, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = candidat.getUserEmail();
        String fromAddress = "houdayfa@gmail.com";
        String subject = "Please verify your registration";
        String content = "Dear [[name]]," + "\n"
                + "Please click the link below to verify your registration:" + "\n"
                + "URL : [[URL]]" + "\n"
                + "Thank you," + "\n"
                + "MT-REKRUTE.";

        String header="";
        content = content.replace("[[name]]",  candidat.getPrenom());
        String verifyURL = siteURL + "/verify/" + candidat.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);

    }



    public void save(MultipartFile file, Long id) throws FileUploadException {
        try {
            String pattern = "MMddyyyyHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String filename=date+file.getOriginalFilename();

            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(filename);
            utilisateurRepository.updateimage(filename,id);
            if (resolve.toFile()
                    .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
        }

    }




}

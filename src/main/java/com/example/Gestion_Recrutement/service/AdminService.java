package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.ExceptionAndFilters.FileUploadException;
import com.example.Gestion_Recrutement.entity.Admin;
import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.ResponsableRH;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import com.example.Gestion_Recrutement.repository.AdminRepository;
import com.example.Gestion_Recrutement.repository.UtilisateurRepository;
import com.example.Gestion_Recrutement.vo.Request;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdminService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final AdminRepository adminRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CompanyService companyService;
    public AdminService(AdminRepository adminRepository,UtilisateurRepository utilisateurRepository,CompanyService companyService){
        this.adminRepository=adminRepository;
        this.utilisateurRepository=utilisateurRepository;
        this.companyService=companyService;
    }

    public Admin updateProfile(Admin admin) throws ParseException {
        String password=passwordEncoder.encode(admin.getUserPass());
        admin.setUserPass(password);
        admin.setTime(LocalTime.now());
        setDate(admin);
        utilisateurRepository.save(admin);
        return admin;
    }

    public Admin findAdminById (Long id){
        Admin admin= adminRepository.findAdminById(id);
        return admin;
    }

    public List<Utilisateur> findListCompte()  {
        return utilisateurRepository.findListCompte();
    }

    public Admin saveCompte(Admin admin) throws ParseException {
        if (utilisateurRepository.findByUserEmail(admin.getUserEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        String password=passwordEncoder.encode(admin.getUserPass());
        admin.setUserPass(password);
        String randomCode = RandomString.make(64);
        admin.setVerificationCode(randomCode);
        admin.setEnabled(false);
        admin.setTime(LocalTime.now());
        setDate(admin);
        utilisateurRepository.save(admin);

        return admin;
    }


    public void setDate(Admin admin)throws ParseException{
        String pattern = "MM-dd-yyyy";
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);
        String stringDate= DateFor.format(date);
        Date date1 = DateFor.parse(stringDate);
        admin.setDate(date1);
    }

    public void save(MultipartFile file, Long id) throws FileUploadException {
        try {
            String pattern = "MMddyyyyHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String filename=date+file.getOriginalFilename();

            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(filename);
            adminRepository.updateimage(filename,id);
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

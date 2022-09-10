package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.ExceptionAndFilters.FileUploadException;
import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Company;
import com.example.Gestion_Recrutement.repository.CandidatRepository;
import com.example.Gestion_Recrutement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompanyService {
    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    private final CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }


    public Company addCompany(Company company) throws ParseException {
        company.setNbrEmployees(0);
        return companyRepository.save(company);
    }

    public List<Company> findAll (){
        List<Company> companies= companyRepository.findAll();
        return companies;
    }

    public void updateNbrEmployees(Long id){
        companyRepository.updateNbrEmployees(id);
    }

    public void save(MultipartFile file, Long id) throws FileUploadException {
        try {
            String pattern = "MMddyyyyHHmmss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String filename=date+file.getOriginalFilename();

            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(filename);
            companyRepository.updateimage(filename,id);
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

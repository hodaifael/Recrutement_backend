package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Offre;
import com.example.Gestion_Recrutement.entity.Postulation;
import com.example.Gestion_Recrutement.repository.CandidatRepository;
import com.example.Gestion_Recrutement.repository.OffreRepository;
import com.example.Gestion_Recrutement.repository.PostulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostulationService {

    @Autowired
    private final PostulationRepository postulationRepository;

    @Autowired
    private final OffreRepository offreRepository;

    public PostulationService(PostulationRepository postulationRepository,OffreRepository offreRepository){
        this.offreRepository=offreRepository;
        this.postulationRepository=postulationRepository;
    }

    public List<Postulation> getPostulation(Long id){
        return postulationRepository.getPostulationsByOffreId(id);
    }


    public Postulation addPostulation(Postulation postulation) throws ParseException {
        setDate(postulation);
        postulation.setTime(LocalTime.now());
        Postulation postulation1= postulationRepository.save(postulation);
        offreRepository.updateNbrPostulation(postulation1.getOffre().getId());
        return postulation1;
    }



    public void setDate(Postulation postulation)throws ParseException{
        String pattern = "MM-dd-yyyy";
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);
        String stringDate= DateFor.format(date);
        Date date1 = DateFor.parse(stringDate);
        postulation.setDate(date1);
    }
}

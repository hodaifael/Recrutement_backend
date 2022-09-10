package com.example.Gestion_Recrutement.service;


import com.example.Gestion_Recrutement.elasticsearchRepository.EsOffreRepository;
import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.OffreLike;
import com.example.Gestion_Recrutement.entity.Offre;
import com.example.Gestion_Recrutement.repository.CandidatRepository;
import com.example.Gestion_Recrutement.repository.LikeRepository;
import com.example.Gestion_Recrutement.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OffreService {

    @Autowired
    private final OffreRepository offreRepository;

    @Autowired
    private final CandidatRepository candidatRepository;

    @Autowired
    private final LikeRepository likeRepository;

    @Autowired
    private final EsOffreRepository esOffreRepository;

    @Autowired
    private JavaMailSender mailSender;

    public OffreService(OffreRepository offreRepository, CandidatRepository candidatRepository, LikeRepository likeRepository, EsOffreRepository esOffreRepository){
        this.offreRepository=offreRepository;
        this.candidatRepository = candidatRepository;
        this.likeRepository = likeRepository;
        this.esOffreRepository=esOffreRepository;
    }

    public int addOffre(Offre offre) throws ParseException, MessagingException, UnsupportedEncodingException {
        Offre offre1=offreRepository.save(offre);
        return recommendation(offre1);
    }

    public OffreLike like(OffreLike like)  {
        OffreLike like1=likeRepository.save(like);
        return like1;
    }

    public int recommendation(Offre offre) throws ParseException, MessagingException, UnsupportedEncodingException {
        List<Candidat> allCandidats=new ArrayList<Candidat>();
        List<Candidat> candidats=candidatRepository.getOffresFirst(offre.getMetier(),offre.getNiveau_d_etude_demande(),offre.getNiveau_d_experience_requise());
        for(int i = 0; i < candidats.size(); i++) {
            allCandidats.add(candidats.get(i));
        }
        List<Candidat> candidats1=candidatRepository.getOffresSecond(offre.getCompany().getId(),offre.getMetier(),offre.getNiveau_d_etude_demande(),offre.getNiveau_d_experience_requise());
        int size;
        if(candidats1.size()%2 ==0){
            size=candidats1.size()/2;
        }else{
            size=candidats1.size()+1/2;
        }

        for(int i = 0; i < size; i++) {
            int compteur=0;
            for(int j = 0; j < candidats.size(); j++) {
                if(candidats1.get(i).getId() == candidats.get(j).getId()){
                    compteur++;
                }
            }
            if(compteur==0){
                allCandidats.add(candidats1.get(i));
            }
        }

        for(int i = 0; i < allCandidats.size(); i++) {
            callsendoffreByEmail(allCandidats.get(i),offre.getId(),offre.getResponsableRH().getUserEmail(),"http://localhost:4200");
        }
        return allCandidats.size();
    }

    public void callsendoffreByEmail(Candidat candidat,Long id,String fAddress, String siteURL){
        try {
            sendoffreByEmail(candidat,id,fAddress,siteURL);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void sendoffreByEmail(Candidat candidat,Long id,String fAddress, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        System.out.println(fAddress);
        String toAddress = candidat.getUserEmail();
        String fromAddress = fAddress;
        String subject = "New Offre";
        String content = "Dear [[name]], " + "\n"
                + "click the link below to see the offre:"+ "\n"
                + "URL : [[URL]]"+"\n"
                + "Thank you,"+"\n"
                + "MT-REKRUTE.";

        content = content.replace("[[name]]", candidat.getPrenom());
        String verifyURL = siteURL + "/singleOffre/" + id;
        content = content.replace("[[URL]]", verifyURL);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message  .setText(content);
        mailSender.send(message);

    }

    public List<Offre> findOffreByFilter(Offre offre){
        return offreRepository.findOffreByFilter(offre.getType_contrat(),offre.getCompany().getSecteur(),
                offre.getCompany().getName(),offre.getMetier(),offre.getNiveau_d_etude_demande(),offre.getNiveau_d_experience_requise());
    }

    public List<Offre> findOffreByAll(){
        return offreRepository.findOffreByAll();
    }

    public Iterable<com.example.Gestion_Recrutement.document.Offre> findOffreByAllElastic(){
        Iterable<com.example.Gestion_Recrutement.document.Offre> offre= esOffreRepository.findAll();
        return offre;
    }

    public List<Offre> getOffres(Long id){
        return offreRepository.getOffres(id);
    }

    public Offre getOffreById(Long id){
        return offreRepository.getOffreById(id);
    }

    public void DeleteOffre(Long id){
        offreRepository.deleteById(id);
    }
}

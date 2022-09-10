package com.example.Gestion_Recrutement.service;


import com.example.Gestion_Recrutement.entity.Commentaire;
import com.example.Gestion_Recrutement.entity.Entretien;
import com.example.Gestion_Recrutement.entity.Postulation;
import com.example.Gestion_Recrutement.repository.CommentaireRepository;
import com.example.Gestion_Recrutement.repository.EntretienRepository;
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
public class CommentaireService {

    @Autowired
    private final CommentaireRepository commentaireRepository;
    public CommentaireService(CommentaireRepository commentaireRepository){
        this.commentaireRepository=commentaireRepository;
    }

    public Commentaire addCommentaire(Commentaire commentaire) throws ParseException {
        setDate(commentaire);
        commentaire.setTime(LocalTime.now());
        commentaireRepository.save(commentaire);
        return commentaire;
    }

    public List<Commentaire> findCommentaireByEntretien (Long id){
        return commentaireRepository.findCommentaireByEntretien(id);
    }

    public void deleteCommentaire (Long id){
        commentaireRepository.deleteCommentaire(id);
    }

    public void setDate(Commentaire commentaire)throws ParseException{
        String pattern = "MM-dd-yyyy";
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);
        String stringDate= DateFor.format(date);
        Date date1 = DateFor.parse(stringDate);
        commentaire.setDate(date1);
    }
}

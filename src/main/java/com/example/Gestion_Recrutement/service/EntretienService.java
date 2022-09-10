package com.example.Gestion_Recrutement.service;


import com.example.Gestion_Recrutement.entity.Entretien;
import com.example.Gestion_Recrutement.repository.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntretienService {

    @Autowired
    private final EntretienRepository entretienRepository;
    private final CommentaireService commentaireService;
    public EntretienService(EntretienRepository entretienRepository, CommentaireService commentaireService){
        this.entretienRepository=entretienRepository;
        this.commentaireService = commentaireService;
    }

    public Entretien addEntretien(Entretien entretien) throws ParseException {
        entretienRepository.save(entretien);
        return entretien;
    }

    public List<Entretien> findEntretiensByRH (Long id){
        return entretienRepository.findEntretiensByRH(id);
    }



    public Optional<Entretien> findEntretiensById (Long id){
        return entretienRepository.findById(id);
    }

    public List<Entretien> findEntretiensByCandidatId (Long id){
        return entretienRepository.findEntretiensByCandidatId(id);
    }

    public void UpdateEtatToCompleted (Long id){
        entretienRepository.UpdateEtatToCompleted(id);
    }

    public void UpdateEtatToRefused (Long id){
        entretienRepository.UpdateEtatToRefused(id);
    }
    public void deleteEntretien (Long id){
        commentaireService.deleteCommentaire(id);
        entretienRepository.deleteEntretien(id);
    }

}

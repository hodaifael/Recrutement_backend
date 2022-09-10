package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.entity.Formation;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import com.example.Gestion_Recrutement.repository.FormationRepository;
import com.example.Gestion_Recrutement.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class FormationService {

    @Autowired
    private final FormationRepository formationRepository;
    public FormationService(FormationRepository formationRepository){
        this.formationRepository=formationRepository;
    }

    public Formation addFormation(Formation formation) throws ParseException {
        return formationRepository.save(formation);
    }

    public List<Formation> findFormationByCondidat (Long id){
        List<Formation> formation= formationRepository.findFormationByCondidat(id);
        return formation;
    }

    public void deleteFormation(Long id){
        formationRepository.deleteById(id);
    }

}

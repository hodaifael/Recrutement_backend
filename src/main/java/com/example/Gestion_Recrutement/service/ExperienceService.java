package com.example.Gestion_Recrutement.service;

import com.example.Gestion_Recrutement.entity.Experience;
import com.example.Gestion_Recrutement.entity.Formation;
import com.example.Gestion_Recrutement.repository.ExperienceRepository;
import com.example.Gestion_Recrutement.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ExperienceService {
    @Autowired
    private final ExperienceRepository experienceRepository;
    public ExperienceService(ExperienceRepository experienceRepository){
        this.experienceRepository=experienceRepository;
    }

    public Experience addExperience(Experience experience) throws ParseException {
        return experienceRepository.save(experience);
    }

    public List<Experience> findExperienceByCondidat (Long id){
        List<Experience> experiences= experienceRepository.findExperienceByCondidat(id);
        return experiences;
    }

    public void deleteExperience(Long id){
        experienceRepository.deleteById(id);
    }

}


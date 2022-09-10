package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Experience;
import com.example.Gestion_Recrutement.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("SELECT c FROM Experience c WHERE c.candidat.id = :condidat_id ")
    List<Experience> findExperienceByCondidat(@Param("condidat_id") Long id);
}

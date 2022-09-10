package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Formation;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

    @Query("SELECT c FROM Formation c WHERE c.candidat.id = :condidat_id ")
    List<Formation> findFormationByCondidat(@Param("condidat_id") Long id);
}

package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {

    @Query("SELECT u FROM Postulation u   WHERE u.offre.id=:offre_id ")
    List<Postulation> getPostulationsByOffreId(@Param("offre_id") Long offre_id);


}

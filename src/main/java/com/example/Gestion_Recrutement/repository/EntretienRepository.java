package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    @Query("SELECT c FROM Entretien c WHERE c.responsableRH.id = :responsableRH_id ")
    List<Entretien> findEntretiensByRH(@Param("responsableRH_id") Long id);



     @Query("SELECT c FROM Entretien c WHERE c.candidat.id = :candidat_id ")
    List<Entretien> findEntretiensByCandidatId(@Param("candidat_id") Long id);


    @Modifying
    @Query("update Entretien u set u.etat = 'Completed' where u.id = ?1")
    void UpdateEtatToCompleted(Long id);

    @Modifying
    @Query("update Entretien u set u.etat = 'Refused' where u.id = ?1")
    void UpdateEtatToRefused(Long id);

    @Modifying
    @Query("delete from Entretien b where b.id=:id")
    void deleteEntretien(@Param("id") Long id);
}

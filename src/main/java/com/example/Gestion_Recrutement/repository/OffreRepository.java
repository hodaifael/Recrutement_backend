package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {


    @Query("SELECT u FROM Offre u WHERE (u.type_contrat =:type_contrat or u.company.secteur=:secteur or u.company.name=:name or u.metier=:metier or u.niveau_d_etude_demande=:niveau_d_etude_demande or u.niveau_d_experience_requise=:niveau_d_experience_requise ) and  DATE(u.date_start_visibilite)<=DATE( NOW() ) and DATE(u.date_end_visibilite)>=DATE( NOW() ) ")
    List<Offre> findOffreByFilter(@Param("type_contrat") String type_contrat, @Param("secteur") String secteur, @Param("name") String name, @Param("metier") String metier,@Param("niveau_d_etude_demande") String niveau_d_etude_demande, @Param("niveau_d_experience_requise") String niveau_d_experience_requise );

    @Query("SELECT u FROM Offre u WHERE  DATE(u.date_start_visibilite)<=DATE( NOW() ) and DATE(u.date_end_visibilite)>=DATE( NOW() ) ")
    List<Offre> findOffreByAll();

    @Query("SELECT u FROM Offre u WHERE u.responsableRH.id=:utilisateur_id ")
    List<Offre> getOffres(@Param("utilisateur_id") Long utilisateur_id);


    @Query("SELECT u FROM Offre u WHERE u.id=:id ")
    Offre getOffreById(@Param("id") Long id);

    @Modifying
    @Query("update Offre u set u.nbrPostulation = u.nbrPostulation+1 where u.id = ?1")
    void updateNbrPostulation(Long id);
}

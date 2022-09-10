package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Offre;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

    @Query("SELECT c FROM Candidat c WHERE c.id = :user_id ")
    Candidat findCandidatById(@Param("user_id") Long id);


    @Modifying
    @Query("update Candidat u set u.image = ?1 where u.id = ?2")
    void updateimage(String image,Long id);

    @Query("SELECT c FROM Candidat c  WHERE   c.specialite=:specialite or c.niveau_d_etude=:niveau_d_etude or c.niveau_d_experience=:niveau_d_experience  ")
    List<Candidat> findListCondidat( @Param("specialite") String specialite, @Param("niveau_d_etude") String niveau_d_etude, @Param("niveau_d_experience") String niveau_d_experience);

    @Query("SELECT u FROM Candidat u WHERE u.specialite=:metier and u.niveau_d_etude=:niveau_d_etude_demande and u.niveau_d_experience=:niveau_d_experience_requise   ")
    List<Candidat> getOffresFirst(@Param("metier") String metier,@Param("niveau_d_etude_demande") String niveau_d_etude_demande,@Param("niveau_d_experience_requise") String niveau_d_experience_requise);



    @Query("SELECT DISTINCT(u)  FROM Candidat u,OffreLike o WHERE u.id=o.candidat.id and o.offre.company.id=:campany_id and ((u.specialite=:metier and u.niveau_d_etude=:niveau_d_etude_demande) or (u.specialite=:metier and u.niveau_d_experience=:niveau_d_experience_requise) or (u.niveau_d_etude=:niveau_d_etude_demande and u.niveau_d_experience=:niveau_d_experience_requise) ) group by o.candidat order by count(o.offre) desc ")
    List<Candidat> getOffresSecond(@Param("campany_id") Long campany_id,@Param("metier") String metier,@Param("niveau_d_etude_demande") String niveau_d_etude_demande,@Param("niveau_d_experience_requise") String niveau_d_experience_requise);




}

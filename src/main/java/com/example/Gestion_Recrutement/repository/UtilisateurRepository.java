package com.example.Gestion_Recrutement.repository;


import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Modifying
    @Query("update Candidat u set u.image = ?1 where u.id = ?2")
    void updateimage(String image,Long id);


    Optional<Utilisateur> findByUserEmail(String userEmail);

    @Modifying
    @Query("update Utilisateur u set u.userEmail = ?2,u.userPass=?3,u.date=?4,u.Time=?5 where u.userEmail = ?1")
    void UpdateCompte(String email, String newEmail, String password, Date date, Date date1);

    @Modifying
    @Query("update Utilisateur u set u.enabled = true where u.id = ?1")
    void UpdateEnabled(Long id);


    @Query("SELECT u FROM Utilisateur u WHERE u.verificationCode=:code ")
    Optional<Utilisateur> verifyCompte(@Param("code") String  code);

    @Query("SELECT u FROM Utilisateur u WHERE u.enabled=false ")
    List<Utilisateur> findListCompte();

    @Query("SELECT c FROM Utilisateur c , Formation f ,Experience e WHERE c.id=f.candidat.id and c.id=e.candidat.id and DATE(c.age) like CONCAT('%',:age,'%') and f.field_study like CONCAT('%',:field_study,'%') and f.degree like CONCAT('%',:degree,'%') and e.field_job like CONCAT('%',:field_job,'%') group by c ")
    List<Utilisateur> findListUtilisateur(@Param("age") String age, @Param("field_study") String field_study, @Param("degree") String degree, @Param("field_job") String field_job );

}

package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.ResponsableRH;
import com.example.Gestion_Recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResponsableRHRepository extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT c FROM ResponsableRH c WHERE c.id = :user_id ")
    ResponsableRH findResponsableRHByID(@Param("user_id") Long id);

    @Modifying
    @Query("update ResponsableRH u set u.image = ?1 where u.id = ?2")
    void updateimage(String image,Long id);

}

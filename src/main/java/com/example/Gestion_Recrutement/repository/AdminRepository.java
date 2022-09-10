package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Admin;
import com.example.Gestion_Recrutement.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Query("update Admin u set u.image = ?1 where u.id = ?2")
    void updateimage(String image,Long id);

    @Query("SELECT c FROM Admin c WHERE c.id = :user_id ")
    Admin findAdminById(@Param("user_id") Long id);
}

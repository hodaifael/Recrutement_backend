package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Modifying
    @Query("update Company u set u.nbrEmployees = u.nbrEmployees+1 where u.id = ?1")
    void updateNbrEmployees(Long id);

    @Modifying
    @Query("update Company u set u.image = ?1 where u.id = ?2")
    void updateimage(String image,Long id);
}

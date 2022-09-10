package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Commentaire;
import com.example.Gestion_Recrutement.entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    @Query("SELECT c FROM Commentaire c WHERE c.entretien.id = :entretien_id ")
    List<Commentaire> findCommentaireByEntretien(@Param("entretien_id") Long id);

    @Modifying
    @Query("delete from Commentaire b where b.entretien.id=:id")
    void deleteCommentaire(@Param("id") Long id);
}


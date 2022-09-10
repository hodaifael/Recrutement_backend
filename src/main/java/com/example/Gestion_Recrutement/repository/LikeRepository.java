package com.example.Gestion_Recrutement.repository;

import com.example.Gestion_Recrutement.entity.Candidat;
import com.example.Gestion_Recrutement.entity.OffreLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<OffreLike, Long> {
    @Query("SELECT c,count(c.offre.id) as nbroffres FROM OffreLike c where c.offre.company.id=:Campny_id  group by c.candidat.id order by nbroffres DESC ")
    List<OffreLike> OrderMaxOrderLike(@Param("Campny_id") Long Campny_id);
}

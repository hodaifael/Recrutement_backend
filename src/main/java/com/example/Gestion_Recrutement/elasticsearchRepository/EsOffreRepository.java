package com.example.Gestion_Recrutement.elasticsearchRepository;

import com.example.Gestion_Recrutement.document.Offre;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsOffreRepository extends ElasticsearchRepository<Offre, String> {
}

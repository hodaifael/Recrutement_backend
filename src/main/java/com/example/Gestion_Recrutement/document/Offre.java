package com.example.Gestion_Recrutement.document;
import com.example.Gestion_Recrutement.helper.Indices;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;


@Document(indexName = Indices.OFFRE_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Offre {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String type_contrat;

    @Field(type = FieldType.Text)
    private String agence;

    @Field(type = FieldType.Text)
    private String metier;

    @Field(type = FieldType.Text)
    private String intitule_post;

    public Offre(com.example.Gestion_Recrutement.entity.Offre offre) {
        this.id = offre.getId();
        this.type_contrat = offre.getType_contrat();
        this.agence = offre.getAgence();
        this.metier = offre.getMetier();
        this.intitule_post = offre.getIntitule_post();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getIntitule_post() {
        return intitule_post;
    }

    public void setIntitule_post(String intitule_post) {
        this.intitule_post = intitule_post;
    }
}

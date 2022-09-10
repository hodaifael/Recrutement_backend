package com.example.Gestion_Recrutement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("Candidat")
@DiscriminatorColumn(name="Utilisateur_type",discriminatorType=DiscriminatorType.STRING)
public final class Candidat extends Utilisateur implements Serializable {

    public Candidat() {
        super();
    }

    @Column
    private String specialite;

    @Column
    private String niveau_d_etude ;

    @Column
    private String niveau_d_experience ;


    @OneToMany(targetEntity = Formation.class, mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "candidat-formation")
    private List<Formation> formations;


    @OneToMany(targetEntity = Experience.class, mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "candidat-experience")
    private List<Experience> experiences;

    @OneToMany(targetEntity = Postulation.class, mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Postulation> postulations;

    @OneToMany(targetEntity = Entretien.class, mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Entretien> entretiens;

    @OneToMany(targetEntity = OffreLike.class, mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OffreLike> likes;

    public List<OffreLike> getLikes() {
        return likes;
    }

    public void setLikes(List<OffreLike> likes) {
        this.likes = likes;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Postulation> getPostulations() {
        return postulations;
    }

    public void setPostulations(List<Postulation> postulations) {
        this.postulations = postulations;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public String getNiveau_d_etude() {
        return niveau_d_etude;
    }

    public void setNiveau_d_etude(String niveau_d_etude) {
        this.niveau_d_etude = niveau_d_etude;
    }

    public String getNiveau_d_experience() {
        return niveau_d_experience;
    }

    public void setNiveau_d_experience(String niveau_d_experience) {
        this.niveau_d_experience = niveau_d_experience;
    }
}

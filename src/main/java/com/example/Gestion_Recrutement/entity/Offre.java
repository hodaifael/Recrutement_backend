package com.example.Gestion_Recrutement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table
public class Offre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column
    private String type_contrat;

    @Column
    private String agence;

    @Column
    private String metier;

    @Column
    private String intitule_post;

    @Column
    private String description_poste;

    @Column
    private Long nbrPostulation;

    @Column
    private String niveau_d_etude_demande ;

    @Column
    private String niveau_d_experience_requise  ;


    @Basic
    @Temporal(TemporalType.DATE)
    private Date date_start_visibilite;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date date_end_visibilite;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsableRH_id", nullable=true)
    @JsonBackReference(value = "Offre-ResponsableRH")
    private ResponsableRH responsableRH;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable=true)
    private Company company;

    @OneToMany(targetEntity = Postulation.class,mappedBy = "offre",fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "postulation-offre")
    private List<Postulation> postulations;

    @OneToMany(targetEntity = Entretien.class,mappedBy = "offre",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Entretien> entretiens;

    @OneToMany(targetEntity = OffreLike.class,mappedBy = "offre",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "like-offre")
    private List<OffreLike> likes;


    public List<OffreLike> getLikes() {
        return likes;
    }

    public void setLikes(List<OffreLike> likes) {
        this.likes = likes;
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

    public String getDescription_poste() {
        return description_poste;
    }

    public void setDescription_poste(String description_poste) {
        this.description_poste = description_poste;
    }

    public Date getDate_start_visibilite() {
        return date_start_visibilite;
    }

    public void setDate_start_visibilite(Date date_start_visibilite) {
        this.date_start_visibilite = date_start_visibilite;
    }

    public Date getDate_end_visibilite() {
        return date_end_visibilite;
    }

    public void setDate_end_visibilite(Date date_end_visibilite) {
        this.date_end_visibilite = date_end_visibilite;
    }

    public ResponsableRH getResponsableRH() {
        return responsableRH;
    }

    public void setResponsableRH(ResponsableRH responsableRH) {
        this.responsableRH = responsableRH;
    }

    public List<Postulation> getPostulations() {
        return postulations;
    }

    public void setPostulations(List<Postulation> postulations) {
        this.postulations = postulations;
    }



    public Long getNbrPostulation() {
        return nbrPostulation;
    }

    public void setNbrPostulation(Long nbrPostulation) {
        this.nbrPostulation = nbrPostulation;
    }

    public List<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public String getNiveau_d_etude_demande() {
        return niveau_d_etude_demande;
    }

    public void setNiveau_d_etude_demande(String niveau_d_etude_demande) {
        this.niveau_d_etude_demande = niveau_d_etude_demande;
    }

    public String getNiveau_d_experience_requise() {
        return niveau_d_experience_requise;
    }

    public void setNiveau_d_experience_requise(String niveau_d_experience_requise) {
        this.niveau_d_experience_requise = niveau_d_experience_requise;
    }
}

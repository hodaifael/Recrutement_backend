package com.example.Gestion_Recrutement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Entretien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String type;

    @Column
    private String etat;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;

    @Basic
    private String start_heure;

    @Basic
    private String end_heure;

    @OneToMany(targetEntity = Commentaire.class,mappedBy = "entretien",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "commentaire-entretien")
    private List<Commentaire> commentaires;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidat_id", nullable=true)
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offre_id", nullable=true)
    private Offre offre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsableRH_id", nullable=true)
    @JsonBackReference(value = "entretien-responsableRH")
    private ResponsableRH responsableRH;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStart_heure() {
        return start_heure;
    }

    public void setStart_heure(String start_heure) {
        this.start_heure = start_heure;
    }

    public String getEnd_heure() {
        return end_heure;
    }

    public void setEnd_heure(String end_heure) {
        this.end_heure = end_heure;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }


    public ResponsableRH getResponsableRH() {
        return responsableRH;
    }

    public void setResponsableRH(ResponsableRH responsableRH) {
        this.responsableRH = responsableRH;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Entretien{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", start_heure='" + start_heure + '\'' +
                ", end_heure='" + end_heure + '\'' +
                ", commentaires=" + commentaires +
                ", candidat=" + candidat +
                ", offre=" + offre +
                ", responsableRH=" + responsableRH +
                '}';
    }
}

package com.example.Gestion_Recrutement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Postulation implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lettreMotivation;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "Time", columnDefinition = "TIME")
    private LocalTime Time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offre_id", nullable=true)
    @JsonBackReference(value = "postulation-offre")
    private Offre offre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condidat_id", nullable=true)
    private Candidat candidat;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    @Override
    public String toString() {
        return "Postulation{" +
                "id=" + id +
                ", lettreMotivation='" + lettreMotivation + '\'' +
                ", date=" + date +
                ", Time=" + Time +
                ", offre=" + offre +
                ", candidat=" + candidat +
                '}';
    }
}

package com.example.Gestion_Recrutement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue("ResponsableRH")
@DiscriminatorColumn(name="Utilisateur_type",discriminatorType=DiscriminatorType.STRING)
public final class ResponsableRH extends Utilisateur implements Serializable {



    @OneToMany(targetEntity = Offre.class,mappedBy = "responsableRH",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Offre-ResponsableRH")
    private List<Offre> offres;

    @OneToMany(targetEntity = Entretien.class,mappedBy = "responsableRH",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "entretien-responsableRH")
    private List<Entretien> entretiens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable=true)
    private Company company;

    public ResponsableRH() {
        super();
    }


    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
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
}

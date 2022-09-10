package com.example.Gestion_Recrutement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;


@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String secteur;

    @Column
    private int nbrEmployees;

    @Column
    private String address;

    @Column
    private String image;


    @OneToMany(targetEntity = ResponsableRH.class,mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Transient
    private List<ResponsableRH> responsableRHS;

    @OneToMany(targetEntity = Company.class,mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Transient
    private List<Offre> offres;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponsableRH> getResponsableRHS() {
        return responsableRHS;
    }

    public void setResponsableRHS(List<ResponsableRH> responsableRHS) {
        this.responsableRHS = responsableRHS;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public int getNbrEmployees() {
        return nbrEmployees;
    }

    public void setNbrEmployees(int nbrEmployees) {
        this.nbrEmployees = nbrEmployees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

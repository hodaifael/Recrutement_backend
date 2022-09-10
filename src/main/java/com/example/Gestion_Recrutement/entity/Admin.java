package com.example.Gestion_Recrutement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@DiscriminatorValue("Admin")
@DiscriminatorColumn(name="Utilisateur_type",discriminatorType=DiscriminatorType.STRING)
public class Admin extends Utilisateur implements Serializable {


    
}

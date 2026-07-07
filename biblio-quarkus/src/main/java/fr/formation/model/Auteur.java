package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import fr.formation.enumerator.NationaliteEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Auteur {
    @Id
    @UuidGenerator
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private NationaliteEnum nationalite;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public NationaliteEnum getNationalite() {
        return nationalite;
    }

    public void setNationalite(NationaliteEnum nationalite) {
        this.nationalite = nationalite;
    }
}

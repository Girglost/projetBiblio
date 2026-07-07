package fr.formation.dto.response;

import fr.formation.enumerator.NationaliteEnum;

public class AuteurResponse {
    private Integer id;
    private String nom;
    private String prenom;
    private NationaliteEnum nationalite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NationaliteEnum getNationalite() {
        return nationalite;
    }

    public void setNationalite(NationaliteEnum nationalite) {
        this.nationalite = nationalite;
    }

    public static AuteurResponse convert(Auteur auteur) {
        AuteurResponse response = new AuteurResponse();

        response.setId(auteur.getId());
        response.setNom(auteur.getNom());
        response.setPrenom(auteur.getPrenom());
        response.setNationalite(auteur.getNationalite());

        return response;
    }
}

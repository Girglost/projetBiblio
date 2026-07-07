package fr.formation.dto.response;

import fr.formation.model.Collection;

public class CollectionResponse {
    private Integer id;
    private String nom;

    public Integer getId() {
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

    public static CollectionResponse convert(Collection collection) {
        CollectionResponse response = new CollectionResponse();

        response.setId(collection.getId());
        response.setNom(collection.getNom());

        return response;
    }
}

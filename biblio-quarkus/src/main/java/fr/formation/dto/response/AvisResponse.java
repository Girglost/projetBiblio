package fr.formation.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.formation.model.Avis;

public class AvisResponse {
    private Integer id;
    private int note;
    private String commentaire;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private Integer livreId;
    private String livreNom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getLivreId() {
        return livreId;
    }

    public void setLivreId(Integer livreId) {
        this.livreId = livreId;
    }

    public String getLivreNom() {
        return livreNom;
    }

    public void setLivreNom(String livreNom) {
        this.livreNom = livreNom;
    }

    public static AvisResponse convert(Avis avis) {
        AvisResponse response = new AvisResponse();

        response.setId(avis.getId());
        response.setNote(avis.getNote());
        response.setCommentaire(avis.getCommentaire());
        response.setDate(avis.getDate());

        if (avis.getLivre() != null) {
            response.setLivreId(avis.getLivre().getId());
            response.setLivreNom(avis.getLivre().getNom());
        }

        return response;
    }
}

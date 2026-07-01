package biblio_boot.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "titre", nullable=false)
    private String titre;
    @Column(name = "résumer", nullable=true)
    private String resumer;
    @Column(name = "année", nullable=false)
    private int annee;
    @Column(name = "auteur", nullable = false)
    private Auteur auteur;
    @Column(name = "éditeur", nullable=false)
    private Editeur editeur;
    @Column(name = "collection", nullable=true)
    private Collection collection;

    public Livre() {}

    public Livre(String titre, String resumer, int annee, Auteur auteur, Editeur editeur, Collection collection) {
        this.titre = titre;
        this.resumer = resumer;
        this.annee = annee;
        this.auteur = auteur;
        this.editeur = editeur;
        this.collection = collection;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getResumer() {
        return resumer;
    }
    public void setResumer(String resumer) {
        this.resumer = resumer;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public Auteur getAuteur() {
        return auteur;
    }
    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
    public Editeur getEditeur() {
        return editeur;
    }
    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }
    public Collection getCollection() {
        return collection;
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
    @Override
    public String toString() {
        return "Livre [id=" + id + ", titre=" + titre + ", resumer=" + resumer + ", annee=" + annee + ", auteur="
                + auteur + ", editeur=" + editeur + ", collection=" + collection + "]";
    }

    
    
}

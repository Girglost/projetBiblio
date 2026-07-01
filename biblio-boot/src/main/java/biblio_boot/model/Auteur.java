package biblio_boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private String nationalite;
	
    
    //getter setter
    public Integer getIdInteger() {
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
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	
	//constructeur
	
	public Auteur(String nom, String prenom, String nationalite) {
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

	//constructeur vide
	
	public Auteur() {}
	
    
	


}

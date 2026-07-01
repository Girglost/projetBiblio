package biblio_boot.model;

import org.springframework.stereotype.Component;

@Component
public class Auteur {

    private String nom;
    private String prenom;
    private String nationalite;
	
    
    //getter setter
    
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

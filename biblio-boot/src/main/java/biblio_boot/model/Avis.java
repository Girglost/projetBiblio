package biblio_boot.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Avis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "note", nullable = false)
	private String note;

	@Column(name = "commentaire", nullable = true)
	private String commentaire;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date", nullable = false)
	private String date;

	@Column(name = "livre", nullable = false)
	private String livre;

	public Avis(String note, String commentaire, String date, String livre) {
		this.note = note;
		this.commentaire = commentaire;
		this.date = date;
		this.livre = livre;
	}

	public Avis() {
	}

	// getter_setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLivre() {
		return livre;
	}

	public void setLivre(String livre) {
		this.livre = livre;
	}

}
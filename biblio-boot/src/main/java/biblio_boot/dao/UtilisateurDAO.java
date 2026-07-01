package biblio_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio_boot.model.Utilisateur;

public interface UtilisateurDAO extends JpaRepository<Utilisateur,Integer> {

    public Utilisateur findByLogin(String login);
}

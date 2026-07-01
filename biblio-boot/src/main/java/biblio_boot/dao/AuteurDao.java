package biblio_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import biblio_boot.model.Auteur;

public interface AuteurDao extends JpaRepository<Auteur, Integer> {

}

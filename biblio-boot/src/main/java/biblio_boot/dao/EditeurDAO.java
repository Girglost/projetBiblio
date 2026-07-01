package biblio_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio_boot.model.Editeur;

public interface EditeurDAO extends JpaRepository<Editeur, Integer> {

}

package biblio_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import biblio_boot.model.Avis;

public interface AvisDAO extends JpaRepository<Avis, Integer> {

}

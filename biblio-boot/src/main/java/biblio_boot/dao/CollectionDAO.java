package biblio_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio_boot.model.Collection;

public interface CollectionDAO extends JpaRepository<Collection, Integer> {
}

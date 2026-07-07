package fr.formation.repo;

import java.util.Optional;

import fr.formation.model.Livre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LivreRepository implements PanacheRepositoryBase<Livre, Integer>{

    public Optional<Livre> findByTitre(String titre) {
        return this.find("titre", titre).firstResultOptional();
    }

}

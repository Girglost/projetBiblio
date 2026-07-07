package fr.formation.repo;

import fr.formation.model.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtilisateurRepository implements PanacheRepositoryBase<Utilisateur, Integer> {

}

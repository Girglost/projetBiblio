package biblio_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.UtilisateurDAO;
import biblio_boot.model.Utilisateur;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurDAO daoUtilisateur;

    public List<Utilisateur> getAll() {
        return daoUtilisateur.findAll();
    }

    public Utilisateur getById(Integer id) {
        return daoUtilisateur.findById(id).orElse(null);
    }

    public Utilisateur insert(Utilisateur utilisateur) {
        return daoUtilisateur.save(utilisateur);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return daoUtilisateur.save(utilisateur);
    }

    public void deleteById(Integer id) {
        daoUtilisateur.deleteById(id);
    }

    public Utilisateur getByLogin(String login) {
        return daoUtilisateur.findByLogin(login);
    }

}

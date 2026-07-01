package biblio_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio_boot.model.Utilisateur;
import biblio_boot.service.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRC {
    @Autowired
    UtilisateurService utilisateurSrv;

    @GetMapping
    public List<Utilisateur> chercherTous() {
        return utilisateurSrv.getAll();
    }

    @GetMapping("/{id}")
    public Utilisateur chercherParId(@PathVariable Integer id) {
        return utilisateurSrv.getById(id);
    }

    @PostMapping
    public Utilisateur ajouter(@RequestBody Utilisateur utilisateur) {
        return utilisateurSrv.insert(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateur modifier(@PathVariable Integer id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id); // On est sur de modifier le bon
        return utilisateurSrv.update(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        utilisateurSrv.deleteById(id);
    }
}

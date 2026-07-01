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

import biblio_boot.model.Editeur;
import biblio_boot.service.EditeurService;

@RestController
@RequestMapping("/api/editeur")
public class EditeurRestController {
    @Autowired
    EditeurService editeurSrv;

    @GetMapping
    public List<Editeur> chercherTous() {
        return editeurSrv.getAll();
    }

    @GetMapping("/{id}")
    public Editeur chercherParId(@PathVariable Integer id) {
        return editeurSrv.getById(id);
    }

    @PostMapping
    public Editeur ajouter(@RequestBody Editeur editeur) {
        return editeurSrv.insert(editeur);
    }

    @PutMapping("/{id}")
    public Editeur modifier(@PathVariable Integer id, @RequestBody Editeur editeur) {
        editeur.setId(id); // On est sur de modifier le bon
        return editeurSrv.update(editeur);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        editeurSrv.deleteById(id);
    }
}

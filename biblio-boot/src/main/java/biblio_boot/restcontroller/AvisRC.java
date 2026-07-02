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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biblio_boot.model.Avis;
import biblio_boot.service.AvisService;

@RestController
@RequestMapping("/api/avis")
public class AvisRC {

    @Autowired
    AvisService avisSrv;

    @GetMapping
    public List<Avis> getAll() {
        return avisSrv.getAllAvis();
    }

    @GetMapping("/{id}")
    public Avis getById(@RequestParam Integer id) {
        return avisSrv.getById(id);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        avisSrv.delete(id);
    }

    @PostMapping
    public Avis ajouter(@RequestBody Avis a) {
        return avisSrv.insert(a);
    }

    @PutMapping("/{id}")
    public Avis modifier(@PathVariable Integer id, @RequestBody Avis a) {
        a.setId(id);
        return avisSrv.update(a);
    }

}

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

import biblio_boot.model.Auteur;
import biblio_boot.service.AuteurService;

@RestController
@RequestMapping("/api/auteur")
public class AuteurRC {

    @Autowired
    AuteurService auteurSrv;

    @GetMapping
    public List<Auteur> getAll(){
        return auteurSrv.getAllAuteurs();
    }

@GetMapping("/${id}")
public Auteur getById(@RequestParam Integer id) {
	return auteurSrv.getById(id);
}

@DeleteMapping("/${id}")
public void supprimer(@PathVariable Integer id)  
{
	auteurSrv.delete(id);
}

@PostMapping
public Auteur ajouter(@RequestBody Auteur a)  
{
	return auteurSrv.insert(a);
}

@PutMapping("/{id}")
public Auteur modifier(@PathVariable Integer id,@RequestBody Auteur a)  
{
	a.setId(id);
	return auteurSrv.update(a);
}
}
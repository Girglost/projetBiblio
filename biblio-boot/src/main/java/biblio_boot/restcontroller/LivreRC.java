package biblio_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio_boot.model.Livre;
import biblio_boot.service.LivreService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/livre")
public class LivreRC {

    @Autowired
    LivreService livreSrv;

    @GetMapping
    public List<Livre> getAll(){
        return livreSrv.getAll();
    }

    @GetMapping("/${id}")
    public Livre getById(@RequestParam Integer id) {
        return livreSrv.getById(id);
    }

    @DeleteMapping("/${id}")
    public void supprimer(@PathVariable Integer id)  
	{
		livreSrv.delete(id);
	}

    @PostMapping
	public Livre ajouter(@RequestBody Livre l)  
	{
		return livreSrv.insert(l);
	}

    @PutMapping("/{id}")
	public Livre modifier(@PathVariable Integer id,@RequestBody Livre l)  
	{
		l.setId(id);
		return livreSrv.update(l);
	}
    




}

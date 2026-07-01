package biblio_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.EditeurDAO;
import biblio_boot.model.Editeur;

@Service
public class EditeurService {
    @Autowired
    EditeurDAO daoEditeur;

    public List<Editeur> getAll() {
        return daoEditeur.findAll();
    }

    public Editeur getById(Integer id) {
        return daoEditeur.findById(id).orElse(null);
    }

    public Editeur insert(Editeur editeur) {
        return daoEditeur.save(editeur);
    }

    public Editeur update(Editeur editeur) {
        return daoEditeur.save(editeur);
    }

    public void deleteById(Integer id) {
        daoEditeur.deleteById(id);
    }

}

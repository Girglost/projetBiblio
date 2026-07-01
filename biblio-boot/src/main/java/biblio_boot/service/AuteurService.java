package biblio_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.AuteurDao;
import biblio_boot.model.Auteur;
import biblio_boot.model.Livre;

@Service
public class AuteurService {

	@Autowired
	AuteurDao auteurDao;

	public List<Auteur> getAllAuteurs() {
		return auteurDao.findAll();
	}

	public Auteur getById(Integer id)
	{
		return auteurDao.findById(id).orElse(null);
	}

	public Auteur insert(Auteur auteur)
	{
		return auteurDao.save(auteur);
	}
	
	public Auteur update(Auteur auteur)
	{
		return auteurDao.save(auteur);
	}

	public void delete(Integer id)
	{
		auteurDao.deleteById(id);
	}
}
package biblio_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.LivreDAO;
import biblio_boot.model.Livre;

@Service
public class LivreService {

    @Autowired
    LivreDAO daoLivre;

    public List<Livre> getAll()
	{
		return daoLivre.findAll();
	}
	public Livre getById(Integer id)
	{
		return daoLivre.findById(id).orElse(null);
	}
    public Livre insert(Livre livre)
	{
		return daoLivre.save(livre);
	}
	public Livre update(Livre livre)
	{
		return daoLivre.save(livre);
	}
	public void delete(Integer id)
	{
		daoLivre.deleteById(id);
	}
}

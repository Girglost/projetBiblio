package biblio_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.AvisDAO;
import biblio_boot.model.Avis;

public class AvisService {
    

    @Autowired
	AvisDAO avisDao;

	public List<Avis> getAllAvis() {
		return avisDao.findAll();
	}

	public Avis getById(Integer id)
	{
		return avisDao.findById(id).orElse(null);
	}

	public Avis insert(Avis avis)
	{
		return avisDao.save(avis);
	}
	
	public Avis update(Avis avis)
	{
		return avisDao.save(avis);
	}

	public void delete(Integer id)
	{
		avisDao.deleteById(id);
	}
}

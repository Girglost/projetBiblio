package biblio_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.CollectionDAO;
import biblio_boot.model.Collection;

@Service
public class CollectionService {

    @Autowired
    CollectionDAO daoCollection;

    public List<Collection> getAll() {
        return daoCollection.findAll();
    }

    public Collection getById(Integer id) {
        return daoCollection.findById(id).orElse(null);
    }

    public Collection insert(Collection collection) {
        return daoCollection.save(collection);
    }

    public Collection update(Collection collection) {
        return daoCollection.save(collection);
    }

    public void deleteById(Integer id) {
        daoCollection.deleteById(id);
    }
}

package biblio_boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio_boot.model.Livre;

public interface LivreDAO extends JpaRepository<Livre,Integer>{
    /* 
    public List<Livre> findByAnneeBetween(int a, int b);
    public List<Livre> findByAnneeLesserThan(int a);
    public List<Livre> findByAnneeGreaterThan(int a);
    public List<Livre> findByTitreLike(String s);
    public Livre findByTitre(String s);
    */
}

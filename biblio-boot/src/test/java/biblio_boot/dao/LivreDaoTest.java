package biblio_boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import biblio_boot.service.LivreService;

@DataJpaTest
public class LivreDaoTest {
    @Autowired
    private LivreService livreSrv;

    
}

package fr.formation.api;

import java.util.List;

import fr.formation.dto.request.CreateOrUpdateLivreRequest;
import fr.formation.dto.response.EntityCreatedResponse;
import fr.formation.dto.response.EntityUpdatedResponse;
import fr.formation.dto.response.LivreResponse;
import fr.formation.model.Livre;
import fr.formation.repo.AuteurRepository;
import fr.formation.repo.CollectionRepository;
import fr.formation.repo.EditeurRepository;
import fr.formation.repo.LivreRepository;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/livre")
public class LivreResource {
    private static Logger log = LoggerFactory.getLogger(LivreResource.class);
    private final LivreRepository repository;
    private final AuteurRepository autRepository;
    private final EditeurRepository ediRepository;
    private final CollectionRepository colRepository;


    public LivreResource(LivreRepository repository,AuteurRepository autRepository, EditeurRepository ediRepository, CollectionRepository colRepository) {
        this.repository = repository;
        this.autRepository = autRepository;
        this.ediRepository = ediRepository;
        this.colRepository = colRepository;
    }

    @GET
    public List<LivreResponse> findAll() {
        log.debug("Liste des livres ...");

        return this.repository.findAll().stream().map(LivreResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public LivreResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche du livre ...");

        return LivreResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @GET
    @Path("/by-nom/{nom}")
    public LivreResponse findByLibelle(@PathParam("nom") String titre) {
        log.debug("Recherche du livre ...");

        return LivreResponse.convert(this.repository.findByTitre(titre).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public EntityCreatedResponse create(@Valid CreateOrUpdateLivreRequest request) {
        log.debug("Création d'un nouveau livre ...");

        Livre livre = new Livre();

        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());
        livre.setAuteur(this.autRepository.findById(request.getAuteurId()));
        livre.setEditeur(this.ediRepository.findById(request.getEditeurId()));
        livre.setCollection(this.colRepository.findById(request.getCollectionId()));

        this.repository.persist(livre);
        
        log.debug("Livre créée !");

        return new EntityCreatedResponse(livre.getId());

    }

    @Transactional
    @PUT
    @Path("/{id}")
    public EntityUpdatedResponse update(@PathParam("id") Integer id, @Valid CreateOrUpdateLivreRequest request) {
        log.debug("Modification du livre ...");

        Livre livre = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());
        livre.setAuteur(this.autRepository.findById(request.getAuteurId()));
        livre.setEditeur(this.ediRepository.findById(request.getEditeurId()));
        livre.setCollection(this.colRepository.findById(request.getCollectionId()));

        this.repository.persist(livre);

        log.debug("Livre modifiée !");

        return new EntityUpdatedResponse(id, true);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression du Livre ...");

        this.repository.deleteById(id);

        log.debug("Livre supprimée !");
    }
}

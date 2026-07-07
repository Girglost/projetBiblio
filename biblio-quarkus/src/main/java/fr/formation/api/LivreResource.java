package fr.formation.api;

import java.util.List;
import java.util.Map;

import fr.formation.model.Livre;
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

    public LivreResource(LivreRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<LivreResponse> findAll() {
        log.debug("Liste des livres ...");

        return this.repository.findAll().stream().map(LivreResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public LivreResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche du livre {} ...", id);

        return LivreResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @GET
    @Path("/by-titre/{titre}")
    public LivreResponse findByLibelle(@PathParam("titre") String titre) {
        log.debug("Recherche du livre {} ...", titre);

        return LivreResponse.convert(this.repository.findByTitre(titre).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(@Valid CreateOrUpdateLivreRequest request) {
        log.debug("Création d'un nouveau livre ...");

        Livre livre = new Livre();

        livre.setLibelle(request.libelle());

        this.repository.persist(livre);

        log.debug("Matière créée !");

        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", livre.getId()))
            .build()
        ;
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid CreateOrUpdateLivreRequest request) {
        log.debug("Modification de la matière {} ...", id);

        Livre livre = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        livre.setLibelle(request.libelle());

        this.repository.persist(livre);

        log.debug("Matière modifiée !");

        return Response.ok(Map.of("id", livre.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression de la matière {} ...", id);

        this.repository.deleteById(id);

        log.debug("Matière supprimée !");

        return Response.noContent().build();
    }
}

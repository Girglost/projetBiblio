package fr.formation.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.dto.request.CreateOrUpdateAvisRequest;
import fr.formation.dto.response.AvisResponse;
import fr.formation.model.Avis;
import fr.formation.repo.AvisRepository;
import fr.formation.repo.LivreRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;



@Path("/api/avis")
public class AvisResource {

private static Logger log = LoggerFactory.getLogger(AvisResource.class);
private final AvisRepository repository;
private final LivreRepository livreRepo;

    
    public AvisResource(AvisRepository repository,LivreRepository livreRepo) {
        this.repository = repository;
        this.livreRepo = livreRepo;
    }

    @GET
    public List<AvisResponse> findAll() {
        log.debug("Liste des avis ...");

        return this.repository.findAll().stream().map(AvisResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public AvisResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche de l'avis {} ...", id);

        return AvisResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(@Valid CreateOrUpdateAvisRequest request) {
        log.debug("Création d'une nouvelle matière ...");

        Avis avis = new Avis();

        avis.setNote(request.getNote());
        avis.setCommentaire(request.getCommentaire());
        if (avis.getDate() == null) {
            avis.setDate(LocalDateTime.now());
        };
        avis.setLivre(this.livreRepo.findById(request.getLivreId()));

        this.repository.persist(avis);

        log.debug("Avis créé !");

        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", avis.getId()))
            .build()
        ;
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid CreateOrUpdateAvisRequest request) {
        log.debug("Modification de la matière {} ...", id);

        Avis avis = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        avis.setNote(request.getNote());
        avis.setCommentaire(request.getCommentaire());
        avis.setLivre(this.livreRepo.findById(request.getLivreId()));

        this.repository.persist(avis);

        log.debug("Matière modifiée !");

        return Response.ok(Map.of("id", avis.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression de l'avis {} ...", id);

        this.repository.deleteById(id);

        log.debug("Avis supprimé !");

        return Response.noContent().build();
    }

}

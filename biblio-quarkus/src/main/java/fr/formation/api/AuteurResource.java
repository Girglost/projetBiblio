package fr.formation.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.dto.request.CreateOrUpdateAuteurRequest;
import fr.formation.dto.response.AuteurResponse;
import fr.formation.model.Auteur;
import fr.formation.repo.AuteurRepository;
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

@Path("/api/auteur")
public class AuteurResource {

    private static Logger log = LoggerFactory.getLogger(AuteurResource.class);
    private final AuteurRepository repository;

    public AuteurResource(AuteurRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<AuteurResponse> findAll() {
        log.debug("Liste des auteur ...");

        return this.repository.findAll().stream().map(AuteurResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public AuteurResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche de l'auteur {} ...", id);

        return AuteurResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(@Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Création d'un nouveau Auteur ...");

        Auteur auteur = new Auteur();

        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.repository.persist(auteur);

        log.debug("Auteur créé !");

        return Response.status(Response.Status.CREATED)
                .entity(Map.of("id", auteur.getId()))
                .build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Modification de l'auteur'{} ...", id);

        Auteur auteur = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

         auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.repository.persist(auteur);

        log.debug("Matière modifiée !");

        return Response.ok(Map.of("id", auteur.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression de l'auteur {} ...", id);

        this.repository.deleteById(id);

        log.debug("Auteur supprimé !");

        return Response.noContent().build();
    }

}

package fr.formation.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.dto.request.CreateOrUpdateEditeurRequest;
import fr.formation.dto.response.EditeurResponse;
import fr.formation.dto.response.EntityCreatedResponse;
import fr.formation.dto.response.EntityUpdatedResponse;
import fr.formation.model.Editeur;
import fr.formation.repo.EditeurRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/editeur")
public class EditeurResource {
    private static Logger log = LoggerFactory.getLogger(EditeurResource.class);
    private final EditeurRepository repo;

    public EditeurResource(EditeurRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @GET
    public List<EditeurResponse> findAll() {
        return this.repo.findAll().stream().map(EditeurResponse::convert).toList();
    }

    @Transactional
    @GET
    @Path("/{id}")
    public EditeurResponse findById(@PathParam("id") Integer id) {
        return EditeurResponse.convert(this.repo.findById(id));
    }

    @Transactional
    @POST
    public EntityCreatedResponse create(@Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Création d'un nouvel editeur  ...");
        Editeur editeur = new Editeur();

        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());
        log.debug("Editeur créé !");
        this.repo.persist(editeur);
        return new EntityCreatedResponse(editeur.getId());
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public EntityUpdatedResponse update(@PathParam("id") Integer id,
            @Valid CreateOrUpdateEditeurRequest request) {
        log.debug("UPDATE d'un Editeur ...");
        Editeur editeur = this.repo.findById(id);
        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        this.repo.persist(editeur);
        log.debug("Editeur Updated ! ");
        return new EntityUpdatedResponse(id, true);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Integer id) {
        this.repo.deleteById(id);
    }
}

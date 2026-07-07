package fr.formation.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.dto.request.CreateOrUpdateCollectionRequest;
import fr.formation.dto.response.CollectionResponse;
import fr.formation.dto.response.EntityCreatedResponse;
import fr.formation.dto.response.EntityUpdatedResponse;
import fr.formation.model.Collection;
import fr.formation.repo.CollectionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/collection")
public class CollectionResource {
    private static Logger log = LoggerFactory.getLogger(EditeurResource.class);
    private final CollectionRepository repo;

    public CollectionResource(CollectionRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @GET

    public List<CollectionResponse> findAll() {
        return this.repo.findAll().stream().map(CollectionResponse::convert).toList();
    }

    @Transactional
    @GET
    @Path("/{id}")
    public CollectionResponse findById(@PathParam("id") Integer id) {
        return CollectionResponse.convert(this.repo.findById(id));
    }

    @Transactional
    @POST
    public EntityCreatedResponse create(@Valid CreateOrUpdateCollectionRequest request) {
        log.debug("Création d'une nouvelle collection  ...");
        Collection collection = new Collection();

        collection.setNom(request.getNom());
        log.debug("collection créée!");
        this.repo.persist(collection);
        return new EntityCreatedResponse(collection.getId());
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public EntityUpdatedResponse update(@PathParam("id") Integer id,
            @Valid CreateOrUpdateCollectionRequest request) {
        log.debug("UPDATE d'une Collection ...");
        Collection collection = this.repo.findById(id);
        collection.setNom(request.getNom());
        this.repo.persist(collection);
        log.debug("Collection Updated ! ");
        return new EntityUpdatedResponse(id, true);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Integer id) {
        this.repo.deleteById(id);
    }
}

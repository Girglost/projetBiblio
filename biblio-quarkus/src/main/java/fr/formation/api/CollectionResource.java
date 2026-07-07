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
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;

public class CollectionResource {
    private static Logger log = LoggerFactory.getLogger(EditeurResource.class);
    private final CollectionRepository repo;

    public CollectionResource(CollectionRepository repo) {
        this.repo = repo;
    }

    public List<CollectionResponse> findAll() {
        return this.repo.findAll().stream().map(CollectionResponse::convert).toList();
    }

    public CollectionResponse findById(@PathParam("id") Integer id) {
        return CollectionResponse.convert(this.repo.findById(id));
    }

    public EntityCreatedResponse create(@Valid CreateOrUpdateCollectionRequest request) {
        log.debug("Création d'une nouvelle collection  ...");
        Collection collection = new Collection();

        collection.setNom(request.getNom());
        log.debug("collection créée!");
        this.repo.persist(collection);
        return new EntityCreatedResponse(collection.getId());
    }

    public EntityUpdatedResponse update(@PathParam("id") Integer id,
            @Valid CreateOrUpdateCollectionRequest request) {
        log.debug("UPDATE d'une Collection ...");
        Collection collection = this.repo.findById(id);
        collection.setNom(request.getNom());
        this.repo.persist(collection);
        log.debug("Collection Updated ! ");
        return new EntityUpdatedResponse(id, true);
    }

    public void deleteById(@PathParam("id") Integer id) {
        this.repo.deleteById(id);
    }
}

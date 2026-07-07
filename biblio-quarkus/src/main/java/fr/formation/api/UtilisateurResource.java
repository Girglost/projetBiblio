package fr.formation.api;

import java.util.Map;

import fr.formation.dto.request.InscriptionRequest;
import fr.formation.model.Utilisateur;
import fr.formation.repo.UtilisateurRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/utilisateur")
public class UtilisateurResource {
    private final UtilisateurRepository repository;

    public UtilisateurResource(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @POST
    @Path("/inscription")
    @Transactional
    public Response inscription(InscriptionRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setLogin(request.login());
        utilisateur.setPassword(BcryptUtil.bcryptHash(request.password()));

        this.repository.persist(utilisateur);

        return Response
                .status(Status.CREATED)
                .entity(Map.of("id", utilisateur.getId()))
                .build();
    }
}

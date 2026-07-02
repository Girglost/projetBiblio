package biblio_boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import biblio_boot.model.Utilisateur;
import biblio_boot.service.UtilisateurService;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurService utilisateurSrv;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = this.utilisateurSrv.getByLogin(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return User.builder()
                .username(username)
                .password(utilisateur.getPassword())
                .build();
    }
}

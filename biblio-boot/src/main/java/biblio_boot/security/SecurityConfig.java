package biblio_boot.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Activer les annotations @PreAuthorize & @PostAuthorize
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configuration des autorisations
        http.authorizeHttpRequests(auth -> {
            // On commence toujours par le plus spécifique, pour terminer par le plus
            // général

            // Les utilisateurs doivent être authentifiés pour accéder à /quelquechose
            auth.requestMatchers("/**").permitAll();

        });
        // Désactivation de la protection CSRF uniquement pour les ressources /api/**
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        // Ici, on utilisera le CorsConfigurationSource défini plus bas
        http.cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
        corsConfiguration.setAllowedMethods(List.of("*"));

        // corsConfiguration.setAllowedHeaders(List.of("Authorization",
        // "Content-Type"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));

        // Pour toutes les ressources, on applique la politique CORS définie plus haut
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

}

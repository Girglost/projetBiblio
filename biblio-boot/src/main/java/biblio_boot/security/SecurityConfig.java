package biblio_boot.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Activer les annotations @PreAuthorize & @PostAuthorize
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtHeaderFilter) throws Exception {
        // Configuration des autorisations
        http.authorizeHttpRequests(auth -> {
            // On commence toujours par le plus spécifique, pour terminer par le plus
            // général
            auth.requestMatchers("/api/auth", "/api/inscription").permitAll();

            auth.requestMatchers("/api/collection/**", "/api/collection").hasRole("ADMIN");
            auth.requestMatchers("/api/auteur/**", "/api/auteur").hasRole("ADMIN");
            auth.requestMatchers("/api/livre/**", "/api/livre").hasRole("ADMIN");
            auth.requestMatchers("/api/editeur/**", "/api/editeur").hasRole("ADMIN");
            // Les utilisateurs doivent être authentifiés pour accéder à /quelquechose
            auth.requestMatchers("/api/**").authenticated();

        });

        // Insérer le filtre AVANT un filtre UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtHeaderFilter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(Customizer.withDefaults());
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

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:81"));

        // Pour toutes les ressources, on applique la politique CORS définie plus haut
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("yohann encoded : " + passwordEncoder.encode("yohann"));
        System.out.println("ronan encoded : " + passwordEncoder.encode("ronan"));
        System.out.println("clea encoded : " + passwordEncoder.encode("clea"));
        System.out.println("marie encoded : " + passwordEncoder.encode("marie"));
        return passwordEncoder;
    }

    // Permet d'ajouter un AuthenticationManager de Spring Security dans le contexte
    // de Spring, pour pouvoir le récupérer ailleurs et l'utiliser
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

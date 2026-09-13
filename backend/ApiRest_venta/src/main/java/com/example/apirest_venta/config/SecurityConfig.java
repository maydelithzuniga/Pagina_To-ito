package com.example.apirest_venta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Configuracion de Spring Security de la API.
 *
 * <p>Como esta es una API para una landing page publica, el catalogo de
 * productos, los testimonios y el contacto de WhatsApp son de lectura
 * publica. Unicamente las operaciones de administracion (crear/editar/
 * eliminar productos, moderar testimonios) requieren autenticacion HTTP
 * Basic con el usuario administrador configurado en application.properties.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.admin.username}")
    private String adminUsername;

    @Value("${app.security.admin.password}")
    private String adminPassword;

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        // Consola H2 (solo desarrollo) y contacto de WhatsApp: publicos.
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/contacto/**").permitAll()
                        // Endpoints de administracion del catalogo: requieren rol ADMIN.
                        .requestMatchers(HttpMethod.GET, "/api/productos/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/productos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/productos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/productos/**").hasRole("ADMIN")
                        // Catalogo: lectura publica para la landing page.
                        .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                        // Moderacion de testimonios: requiere rol ADMIN.
                        .requestMatchers(HttpMethod.GET, "/api/testimonios/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/testimonios/**").hasRole("ADMIN")
                        // Testimonios: lectura publica y cualquier comprador puede dejar el suyo.
                        .requestMatchers(HttpMethod.GET, "/api/testimonios/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/testimonios/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(basic -> {});

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .toList());
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

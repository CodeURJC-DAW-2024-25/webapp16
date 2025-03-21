package com.daw.daw.security;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.daw.daw.security.jwt.JwtRequestFilter;
import com.daw.daw.security.jwt.UnauthorizedHandlerJwt;

@Configuration
@EnableWebSecurity
public class Security {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Autowired
    private CSRFHandlerConfiguration CSRFHandlerConfiguration;

    // Eliminar la inyección del AuthenticationManager en el constructor
    public Security() {}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        http
            .securityMatcher("/api/**")
            .exceptionHandling(handling -> handling
                .authenticationEntryPoint(unauthorizedHandlerJwt));

        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()
                .anyRequest().permitAll()
            );

        http.formLogin(formLogin -> formLogin.disable());
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(httpBasic -> httpBasic.disable());
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2) // Cambia el orden para evitar conflictos
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/css/**", "/img/**", "/js/**", "/videos/**", "/imgEvent/**").permitAll()
            .requestMatchers("/users/authenticate", "/users/create", "/favicon/**", "/events/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/perfil/**", "/paginaperfil/**", "/users/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/tickets/buyTicket", "/tickets/**").hasRole("USER")
            .requestMatchers("/events/partyCreate", "/events/conciertoCreate", "/events/**").hasRole("ADMIN")
            .requestMatchers("/reserva/aceptar", "/reserva/rechazar", "/reserva/deleteReserva").hasRole("ADMIN")
            .requestMatchers("/application/pdf", "/coments/create", "users/profileImg/**", "/users/updateUser").hasRole("USER")
            .anyRequest().permitAll()
        );

        http.formLogin(formLogin -> formLogin
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        );

        http.logout(logout -> logout
            .logoutUrl("/users/logout")
            .logoutSuccessUrl("/")
            .permitAll()
        );

        return http.build();
    }
}

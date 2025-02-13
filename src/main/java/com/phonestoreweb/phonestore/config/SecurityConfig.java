package com.phonestoreweb.phonestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {"/","/login","/register","/auth/login","/auth/logout","/auth/refreshToken"
            ,"/auth/introspect","/product/**","/products/**"};

    private final String[] RESOURCES = {"/resources/**","/static/**", "/web/**","/adminn/**","/css/**", "/fonts/**", "/js/**"};

    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Autowired
    private CustomJwtDecoder customJwtDecoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request ->
                        request.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                                .requestMatchers(RESOURCES).permitAll()
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()
                        )
               ;


//        http.oauth2ResourceServer(oauth2 ->
//                oauth2.jwt(jwtConfigurer
//                            -> jwtConfigurer.decoder(customJwtDecoder)
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())

  //              );
        http.formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return  jwtAuthenticationConverter;
    }


}

package pl.fitnote.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final AuthConfig authConfig;

    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        JwtAuthenticationConverter conv = new JwtAuthenticationConverter();
        conv.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return conv;
    }
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//
//        String STANDARD_USER = "STANDARD_USER";
//        String ADMIN = "ADMIN";
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .cors().configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration config = new CorsConfiguration();
//                        config.setAllowedOrigins(Collections.singletonList("http://localhost:8100"));
//                        config.setAllowedMethods(Collections.singletonList("*"));
//                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
//                        config.setExposedHeaders(List.of("Authorization"));
//                        config.setMaxAge(3600L);
//                        return config;
//                    }
//                }).and().csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .authorizeHttpRequests()
//                .requestMatchers("/users").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/users/settings").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/exercises").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/exercises/*").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/training-plans").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/training-plans/**").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/trainings/**").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/trainings").hasAnyRole(STANDARD_USER, ADMIN)
//                .requestMatchers("/anonymous").permitAll()
////                .requestMatchers("/user").permitAll()
////                .requestMatchers("/*").permitAll()
//                .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
//        return http.build();
//    }





    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        Map<String, AuthenticationManager> authenticationManagers = new HashMap<>();
        authConfig.getJwtIssuers().forEach(issuer -> {
            JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(JwtDecoders.fromOidcIssuerLocation(issuer));
            authenticationProvider.setJwtAuthenticationConverter(getJwtAuthenticationConverter());
            authenticationManagers.put(issuer, authenticationProvider::authenticate);
        });
        JwtIssuerAuthenticationManagerResolver authenticationManagerResolver =
                new JwtIssuerAuthenticationManagerResolver(authenticationManagers::get);

        http.cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(authConfig.getAllowedOrigins());
                    config.setAllowedMethods(authConfig.getAllowedMethods());
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(authConfig.getAllowedHeaders());
                    config.setMaxAge(3600L);
                    return config;
                })
                .and()
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .and()
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .authorizeHttpRequests()
                .requestMatchers("/users").authenticated()
                .requestMatchers("/users/settings").authenticated()
                .requestMatchers("/exercises").authenticated()
                .requestMatchers("/exercises/*").authenticated()
                .requestMatchers("/training-plans").authenticated()
                .requestMatchers("/training-plans/**").authenticated()
                .requestMatchers("/trainings/**").authenticated()
                .requestMatchers("/trainings").authenticated()
                .requestMatchers("/**").authenticated()
                .and()
                .oauth2ResourceServer()
                .authenticationManagerResolver(authenticationManagerResolver);

        return http.build();
    }

}

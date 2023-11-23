package pl.fitnote.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final AuthConfig authConfig;

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .cors().configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(authConfig.getAllowedOrigins());
//                    config.setAllowedMethods(authConfig.getAllowedMethods());
//                    config.setAllowCredentials(true);
//                    config.setAllowedHeaders(authConfig.getAllowedHeaders());
//                    config.setExposedHeaders(List.of("Authorization"));
//                    config.setMaxAge(3600L);
//                    return config;
//                })
//                .and()
//                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new JWTTokenGenerationFilter(authConfig), BasicAuthenticationFilter.class)
//                .addFilterBefore(new JWTTokenValidationFilter(authConfig), BasicAuthenticationFilter.class)
//                .authorizeHttpRequests()
//                .requestMatchers("/register").permitAll()
//                .requestMatchers("/login").permitAll()
//                .requestMatchers("/user").authenticated()
//                .requestMatchers("/user/settings").authenticated()
//                .requestMatchers("/user/login").permitAll()
//                .requestMatchers("/exercises").authenticated()
//                .requestMatchers("/exercises/*").authenticated()
//                .requestMatchers("/training-plans").authenticated()
//                .requestMatchers("/training-plans/**").authenticated()
//                .requestMatchers("/trainings/**").authenticated()
//                .requestMatchers("/trainings").authenticated()
//                .requestMatchers("/**").authenticated()
//                .and().formLogin()
//                .and().httpBasic();
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(authConfig.getAllowedOrigins());
                    config.setAllowedMethods(authConfig.getAllowedMethods());
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(authConfig.getAllowedHeaders());
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                })
                .and()
                .csrf().disable()
//                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGenerationFilter(authConfig), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidationFilter(authConfig), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/user").authenticated()
                .requestMatchers("/user/settings").authenticated()
                .requestMatchers("/user/login").permitAll()
                .requestMatchers("/exercises").authenticated()
                .requestMatchers("/exercises/*").authenticated()
                .requestMatchers("/training-plans").authenticated()
                .requestMatchers("/training-plans/**").authenticated()
                .requestMatchers("/trainings/**").authenticated()
                .requestMatchers("/trainings").authenticated()
                .requestMatchers("/body-measurements/**").authenticated()
                .requestMatchers("/general-measurements").authenticated()
                .requestMatchers("/general-measurements/**").authenticated()
                .requestMatchers("/**").authenticated()
                .and()
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}

package pl.fitnote.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.security")
@Getter
@Setter
class AuthConfig {
    private String jwtKey = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    private String jwtHeader = "Authorization";
    private String issuer = "FitNote";
    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
}

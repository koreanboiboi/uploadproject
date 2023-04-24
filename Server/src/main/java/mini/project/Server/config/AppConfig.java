package mini.project.Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .maxAge(3600);
                        
                registry.addMapping("/api/token")
                        .allowedOrigins("https://accounts.spotify.com")
                        .allowedMethods("OPTIONS","GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}

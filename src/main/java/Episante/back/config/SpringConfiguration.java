package Episante.back.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class SpringConfiguration implements WebMvcConfigurer {
    @Override   
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5174",
                        "http://localhost:5173",
                        "http://172.31.250.99:5173",
                        "http://172.31.250.99:3000",
                        "http://192.168.1.3:5173"

                )
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}









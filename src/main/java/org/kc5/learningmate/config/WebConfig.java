package org.kc5.learningmate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOriginPatterns("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // 허용할 HTTP 메서드
                        .allowedHeaders("*")                                     // 허용할 헤더
                        .exposedHeaders("Authorization", "X-Request-Id")        // 클라이언트에서 접근 가능한 응답 헤더
                        .allowCredentials(true)                                 // 쿠키 인증 허용 여부
                        .maxAge(3600);
            }
        };
    }
}

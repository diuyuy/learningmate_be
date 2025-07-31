package org.kc5.learningmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LearningmateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningmateApplication.class, args);
    }

}

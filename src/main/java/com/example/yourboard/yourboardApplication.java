package com.example.yourboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class yourboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(yourboardApplication.class, args);
    }

}

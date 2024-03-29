package com.complaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class ComplaintSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintSystemApplication.class, args);
    }

}

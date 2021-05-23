package com.db.datastoreserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DatastoreserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatastoreserverApplication.class, args);
    }

}

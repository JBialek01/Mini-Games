package pl.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class MainSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(MainSpringBoot.class, args);
    }
}



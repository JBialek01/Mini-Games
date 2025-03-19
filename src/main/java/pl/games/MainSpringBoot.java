package pl.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.games.lotek.domain.model.UserHitsRankingEntity;
import pl.games.lotek.domain.repository.UserHitsRankingRepository;

import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
@EnableMongoRepositories
public class MainSpringBoot {



    public static void main(String[] args) {
        SpringApplication.run(MainSpringBoot.class, args);
    }


}


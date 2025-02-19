package pl.games.lotek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

public interface LotekRepository extends MongoRepository<LotekEntity, String> {

    LotekEntity save(LotekEntity lotekEntity);

//    Map<String, LotekEntity> fetchAll();

    List<LotekEntity> findAll();
}

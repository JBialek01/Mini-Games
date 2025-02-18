package pl.games.lotek.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LotekRepositoryInMemoryDb implements LotekRepository {

    static Map<String, LotekEntity> db = new HashMap<>();

    public LotekRepositoryInMemoryDb() {
    }

    @Override
    public void save(LotekEntity lotekEntity) {
        db.put(lotekEntity.getId(), lotekEntity);
    }

    @Override
    public Map<String, LotekEntity> fetchAll() {
        return db;
    }
}

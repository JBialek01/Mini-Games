package pl.games.lotek.repository;

import java.util.Map;

public interface LotekRepository {


    void save(LotekEntity lotekEntity);

    Map<String, LotekEntity> fetchAll();
}

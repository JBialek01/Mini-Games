package pl.games.app.lotek;

import java.util.Map;

public interface LotekRepository {


    void save(LotekEntity lotekEntity);

    Map<String, LotekEntity> fetchAll();
}

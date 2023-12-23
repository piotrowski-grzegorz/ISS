package db;

import java.util.HashMap;
import java.util.Map;

public class ISSCLDb {
    private static final Map<Long, ISSCLDataEntity> database = new HashMap<>();
    private Long ISSCL_DATA_ENTITY_ID = 1L;
    //CRUD

    public ISSCLDataEntity add(ISSCLDataEntity entity) {
        final Long newIdISSCLDataEntity = ISSCL_DATA_ENTITY_ID;
        entity.setId(newIdISSCLDataEntity);
        database.put(entity.getId(), entity);
        ISSCL_DATA_ENTITY_ID = ISSCL_DATA_ENTITY_ID + 1;
        return entity;
    }
    public ISSCLDataEntity get(Long id) {

        return database.get(id);
    }
    public ISSCLDataEntity delete() {
        return null;
    }
}

package ar.utn.frba.dds.logger.adapters;

import java.util.Map;

public interface DatabaseAdapter {
    void connect();

    void insert(String errorLog, Map<String, Object> stringObjectMap);

    void disconnect();
}

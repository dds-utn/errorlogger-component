package ar.utn.frba.dds.logger.adapters;

import ar.utn.frba.dds.logger.adapters.connectors.MongoDBConnector;

import java.io.IOException;
import java.util.Map;

import ar.utn.frba.dds.logger.utils.ConfigReader;
import org.bson.Document;


public class MongoDBDatabaseAdapter implements DatabaseAdapter {
    private MongoDBConnector mongoDBConnector;
    private ConfigReader config;

    public MongoDBDatabaseAdapter() {
        this.mongoDBConnector = new MongoDBConnector();
        this.config = new ConfigReader();
    }

    @Override
    public void connect() {
        try {
            this.mongoDBConnector.connect(this.config.getProperty("mongoConnectionString"), this.config.getProperty("mongoDatabaseName"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(String errorLog, Map<String, Object> stringObjectMap) {
        Document document = new Document();

        for(Map.Entry<String, Object> entry: stringObjectMap.entrySet()) {
            document.append(entry.getKey(), entry.getValue());
        }

        this.mongoDBConnector.insert(errorLog, document);
    }

    @Override
    public void disconnect() {
        this.mongoDBConnector.disconnect();
    }
}

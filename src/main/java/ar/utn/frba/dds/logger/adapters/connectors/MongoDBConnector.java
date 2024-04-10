package ar.utn.frba.dds.logger.adapters.connectors;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDBConnector {
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Método para conectarse a la base de datos
    public void connect(String connectionString, String databaseName) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(databaseName);
        System.out.println("Conexión establecida con la base de datos: " + databaseName);
    }

    // Método para desconectarse de la base de datos
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Desconexión exitosa");
        }
    }

    // Método para insertar un registro en un documento
    public void insert(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
        System.out.println("Registro insertado en la colección: " + collectionName);
    }
}

package com.sayfr.deviceservice.data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.sayfr.deviceservice.domain.Device;
import com.sayfr.deviceservice.domain.Record;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class MongoDBUtil {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBUtil.class);
    @Value("${spring.data.mongodb.database}")
    private static final String DATABASE_NAME = "Devices_Service";
    private static MongoClient mongoClient;

    static {
        try {
            String connectionString = "mongodb://localhost:27017";
            mongoClient = MongoClients.create(connectionString);
        } catch (Exception e) {
            logger.error("Failed to initialize MongoDB client", e);
            throw new RuntimeException("MongoDB client initialization failed", e);
        }
    }

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase(DATABASE_NAME);
    }

    public static MongoCollection<Document> getCollection(String collectionName) {
        return getDatabase().getCollection(collectionName);
    }

    public static void closeMongoClient() {
        if (mongoClient != null) {
            mongoClient.close();
            logger.info("MongoDB client closed");
        }
    }

    public static void createCollectionIfNotExists(String collectionName) {
        try {
            MongoDatabase database = getDatabase();
            if (!collectionExists(database, collectionName)) {
                database.createCollection(collectionName);
                logger.info("Collection '{}' created", collectionName);
            } else {
                logger.info("Collection '{}' already exists", collectionName);
            }
        } catch (Exception e) {
            logger.error("Failed to create collection '{}'", collectionName, e);
            throw new RuntimeException("Collection creation failed", e);
        }
    }

    public static void createCollectionIfNotExists(String collectionName, CreateCollectionOptions collectionOptions) {
        try {
            MongoDatabase database = getDatabase();
            if (!collectionExists(database, collectionName)) {
                database.createCollection(collectionName, collectionOptions);
                logger.info("Collection '{}' created", collectionName);
            } else {
                logger.info("Collection '{}' already exists", collectionName);
            }
        } catch (Exception e) {
            logger.error("Failed to create collection '{}'", collectionName, e);
            throw new RuntimeException("Collection creation failed", e);
        }
    }

    private static boolean collectionExists(MongoDatabase database, String collectionName) {
        for (String existingCollection : database.listCollectionNames()) {
            if (existingCollection.equals(collectionName)) {
                return true;
            }
        }
        return false;
    }

    public static class Transformer {
        public static Document recordToDocument(Record record) {
            Document recordDocument = new Document()
                    .append("_id", record.getRecordId().toString())
                    .append("patientId", record.getPatientId().toString())
                    .append("sensorId", record.getSensorId().toString());
            return recordDocument;
        }

        public static Document deviceToDocument(Device device) {
            return new Document()
                .append("_id", device.getDeviceId().toString())
                .append("name", device.getName())
                .append("sensors",device.getSensorIds());
        }

        public static Device documentToDevice(Document deviceDocument) {
            return null;
        }
    }

}

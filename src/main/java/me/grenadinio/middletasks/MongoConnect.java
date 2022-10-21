package me.grenadinio.middletasks;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Function;

public class MongoConnect {

    public static <T> T execute(Function<MongoCollection<Document>, T> query) {
        String ip = "localhost";
        int port = 27017;
        try (MongoClient client = MongoClients.create("mongodb://" + ip + ":" + port)) {
            MongoDatabase mcserverdb = client.getDatabase("minecraftserver");
            MongoCollection<Document> collection = mcserverdb.getCollection("teleporters");

            return query.apply(collection);
        }
    }
}

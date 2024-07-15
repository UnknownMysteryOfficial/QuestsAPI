package org.mrerrorx.questsapi;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import org.mrerrorx.questsapi.QuestsAPI;
import org.bson.Document;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MongoDBImpl implements Database {
    private final MongoCollection<Document> collection;

    public MongoDBImpl(String uri, String databaseName, String collectionName) {
        MongoClient client = MongoClients.create(uri);
        MongoDatabase database = client.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    @Override
    public CompletableFuture<Quest> getQuest(UUID playerId) {
        return CompletableFuture.supplyAsync(() -> {
            Document doc = collection.find(new Document("playerId", playerId.toString())).first();
            return doc != null ? Quest.fromDocument(doc) : null;
        });
    }

    @Override
    public CompletableFuture<Void> saveQuest(UUID playerId, Quest quest) {
        return CompletableFuture.runAsync(() -> {
            Document doc = quest.toDocument();
            doc.append("playerId", playerId.toString());
            collection.replaceOne(new Document("playerId", playerId.toString()), doc, new ReplaceOptions().upsert(true));
        });
    }
}

package org.mrerrorx.questsapi;

import org.mrerrorx.questsapi.Database;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class QuestManager {
    private final Database database;
    private final Map<UUID, Quest> questCache = new ConcurrentHashMap<>();

    public QuestManager(Database database) {
        this.database = database;
    }

    public Map<UUID, Quest> getQuestCache() {
        return questCache;
    }

    public CompletableFuture<Quest> loadQuest(UUID playerId) {
        return database.getQuest(playerId).thenApply(quest -> {
            if (quest != null) {
                questCache.put(playerId, quest);
            }
            return quest;
        });
    }

    public void saveQuest(UUID playerId) {
        Quest quest = questCache.get(playerId);
        if (quest != null) {
            database.saveQuest(playerId, quest);
        }
    }
}
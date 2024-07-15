package org.mrerrorx.questsapi;

import org.mrerrorx.questsapi.QuestsAPI;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Database {
    CompletableFuture<Quest> getQuest(UUID playerId);
    CompletableFuture<Void> saveQuest(UUID playerId, Quest quest);
}

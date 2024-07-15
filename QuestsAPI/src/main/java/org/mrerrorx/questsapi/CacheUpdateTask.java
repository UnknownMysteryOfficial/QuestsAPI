package org.mrerrorx.questsapi;

import org.mrerrorx.questsapi.Quest;
import org.mrerrorx.questsapi.QuestManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class CacheUpdateTask extends BukkitRunnable {
    private final QuestManager questManager;

    public CacheUpdateTask(QuestManager questManager) {
        this.questManager = questManager;
    }

    @Override
    public void run() {
        Map<UUID, Quest> questCache = questManager.getQuestCache();
        long currentTime = System.currentTimeMillis();

        for (Map.Entry<UUID, Quest> entry : questCache.entrySet()) {
            UUID playerId = entry.getKey();
            Quest quest = entry.getValue();

            // Update quest progress in the cache
            quest.setLastUpdate(currentTime);
            questManager.saveQuest(playerId);
        }
    }
}

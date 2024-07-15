package org.mrerrorx.questsapi;

public class QuestCache {
    private Quest quest;
    private long lastUpdate;

    public QuestCache(Quest quest) {
        this.quest = quest;
        this.lastUpdate = System.currentTimeMillis();
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

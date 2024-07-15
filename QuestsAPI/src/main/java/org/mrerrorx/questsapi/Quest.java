package org.mrerrorx.questsapi;

import org.bson.Document;

public abstract class Quest {
    private final String questName;
    private int level;
    private double progress;
    private long lastUpdate;

    public Quest(String questName, int level, double progress) {
        this.questName = questName;
        this.level = level;
        this.progress = progress;
        this.lastUpdate = System.currentTimeMillis();
    }

    public String getQuestName() {
        return questName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public abstract void updateProgress(double amount);

    public abstract boolean checkLevelUp();

    public Document toDocument() {
        return new Document("questName", questName)
                .append("level", level)
                .append("progress", progress)
                .append("type", getClass().getSimpleName())
                .append("lastUpdate", lastUpdate);
    }

    public static Quest fromDocument(Document doc) {
        String type = doc.getString("type");
        String questName = doc.getString("questName");
        int level = doc.getInteger("level");
        double progress = doc.getDouble("progress");
        long lastUpdate = doc.getLong("lastUpdate");

        Quest quest;
        switch (type) {
            case "WalkingQuest":
                quest = new WalkingQuest(questName, level, progress);
                break;
            case "BlockBreakingQuest":
                quest = new BlockBreakingQuest(questName, level, progress);
                break;
            default:
                throw new IllegalArgumentException("Unknown quest type: " + type);
        }
        quest.setLastUpdate(lastUpdate);
        return quest;
    }
}
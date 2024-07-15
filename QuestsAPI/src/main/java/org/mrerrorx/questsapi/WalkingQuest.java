package org.mrerrorx.questsapi;

public class WalkingQuest extends Quest {
    private static final double DISTANCE_PER_LEVEL = 1000.0; // Example value

    public WalkingQuest(String questName, int level, double progress) {
        super(questName, level, progress);
    }

    @Override
    public void updateProgress(double amount) {
        setProgress(getProgress() + amount);
    }

    @Override
    public boolean checkLevelUp() {
        if (getProgress() >= DISTANCE_PER_LEVEL) {
            setLevel(getLevel() + 1);
            setProgress(getProgress() - DISTANCE_PER_LEVEL);
            return true;
        }
        return false;
    }
}

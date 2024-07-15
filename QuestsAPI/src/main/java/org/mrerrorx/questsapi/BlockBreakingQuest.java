package org.mrerrorx.questsapi;

public class BlockBreakingQuest extends Quest {
    private static final int BLOCKS_PER_LEVEL = 100; // Example value

    public BlockBreakingQuest(String questName, int level, double progress) {
        super(questName, level, progress);
    }

    @Override
    public void updateProgress(double amount) {
        setProgress(getProgress() + amount);
    }

    @Override
    public boolean checkLevelUp() {
        if (getProgress() >= BLOCKS_PER_LEVEL) {
            setLevel(getLevel() + 1);
            setProgress(getProgress() - BLOCKS_PER_LEVEL);
            return true;
        }
        return false;
    }
}
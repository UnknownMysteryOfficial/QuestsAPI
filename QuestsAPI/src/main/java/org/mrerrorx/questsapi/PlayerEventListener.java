package org.mrerrorx.questsapi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEventListener implements Listener {

    private final QuestManager questManager;

    public PlayerEventListener(QuestManager questManager) {
        this.questManager = questManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Handle player movement and update quest progress
    }
}

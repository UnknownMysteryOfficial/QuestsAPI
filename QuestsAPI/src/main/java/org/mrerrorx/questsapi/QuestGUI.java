package org.mrerrorx.questsapi;

import org.mrerrorx.questsapi.Quest;
import org.mrerrorx.questsapi.QuestManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestGUI implements Listener {
    private final QuestManager questManager;
    private final Plugin plugin;

    public QuestGUI(QuestManager questManager, Plugin plugin) {
        this.questManager = questManager;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void openQuestMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Quest Menu");

        // Add items representing quests
        UUID playerId = player.getUniqueId();
        questManager.loadQuest(playerId).thenAccept(quest -> {
            if (quest != null) {
                ItemStack questItem = createQuestItem(quest);
                inventory.setItem(0, questItem);
                player.openInventory(inventory);
            }
        });
    }

    private ItemStack createQuestItem(Quest quest) {
        // Create an ItemStack representing the quest
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(quest.getQuestName());
        List<String> lore = new ArrayList<>();
        lore.add("Level: " + quest.getLevel());
        lore.add("Progress: " + quest.getProgress());
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Quest Menu")) {
            event.setCancelled(true);
            // Handle quest item clicks
        }
    }
}

package org.mrerrorx.questsapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuestsAPI extends JavaPlugin {
    private QuestManager questManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        String uri = config.getString("mongodb.uri");
        String databaseName = config.getString("mongodb.database");
        String collectionName = config.getString("mongodb.collection");

        // Initialize the database and quest manager
        Database database = new MongoDBImpl(uri, databaseName, collectionName);
        questManager = new QuestManager(database);

        // Register event listeners, commands, etc.
        QuestGUI questGUI = new QuestGUI(questManager, this);
        getCommand("quests").setExecutor(new QuestCommand(questGUI));

        // Schedule cache updates
        int updateInterval = config.getInt("cache.updateInterval");
        new CacheUpdateTask(questManager).runTaskTimer(this, 0, updateInterval * 20);
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
package org.mrerrorx.questsapi;

import org.mrerrorx.questsapi.QuestGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCommand implements CommandExecutor {
    private final QuestGUI questGUI;

    public QuestCommand(QuestGUI questGUI) {
        this.questGUI = questGUI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            questGUI.openQuestMenu(player);
            return true;
        }
        return false;
    }
}

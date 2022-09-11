package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.util.ConfigLocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SethomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            new ConfigLocationUtil(player.getLocation(), player.getName() + ".Home").saveLocation();
            player.sendMessage("§aYou've set your home point.");
        }
        return true;
    }
}

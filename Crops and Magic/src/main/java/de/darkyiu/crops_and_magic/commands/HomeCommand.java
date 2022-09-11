package de.darkyiu.crops_and_magic.commands;


import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.util.ConfigLocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(Main.getPlugin().getHomesFile().contains(player.getName() + ".Home")){
                player.teleport(new ConfigLocationUtil(player.getName() + ".Home").loadLocation());
                player.sendMessage("§aDu hast dich zu deinem Zuhause teleportiert.");
            }else {
                player.sendMessage("§cDu hast noch kein Home. Benutze /sethome um eins zu machen.");
            }
        }
        return false;
    }
}

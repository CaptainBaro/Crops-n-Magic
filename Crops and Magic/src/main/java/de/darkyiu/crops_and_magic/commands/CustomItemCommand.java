package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.relics.Relic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CustomItemCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length==1){
                if (CustomItemBuilder.getCustomItem(args[0])!=null){
                    Player player = (Player) sender;
                    player.getInventory().addItem(new CustomItemBuilder(CustomItemBuilder.getCustomItem(args[0])).build());
                }
            }else {
                sender.sendMessage("§cPlease use /cui <cui>");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if(args.length==1){
            for (CustomItems customItem : CustomItems.values()){
                list.add(customItem.getLocalizedName());
            }
            return list;
        }else return null;
    }
}

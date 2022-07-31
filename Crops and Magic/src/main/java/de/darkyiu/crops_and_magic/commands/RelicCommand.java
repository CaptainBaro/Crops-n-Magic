package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.spells.Spell;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RelicCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length==1){
                if (CustomItemBuilder.getRelic(args[0])!=null){
                    Player player = (Player) sender;
                    player.getInventory().addItem(new CustomItemBuilder(CustomItemBuilder.getRelic(args[0])).createRelic());
                }
            }else {
                sender.sendMessage("§cPlease use /relic <relic>");
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if(args.length==1){
            for (Relic customItem : Relic.values()){
                list.add(customItem.getLocalizedName());
            }
            return list;
        }else return null;
    }
}

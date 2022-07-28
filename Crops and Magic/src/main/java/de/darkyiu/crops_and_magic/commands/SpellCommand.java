package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.listeners.PlantListener;
import de.darkyiu.crops_and_magic.spells.Spell;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;

public class SpellCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if(args.length==1){
                if (CustomItemBuilder.getSpell(args[0])!=null){
                    player.getInventory().addItem(new CustomItemBuilder(CustomItemBuilder.getSpell(args[0])).createSpell());
                }
            }else {
                player.sendMessage("§cPlease use /spell <spell>");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if(args.length==1){
            for (Spell customItem : Spell.values()){
                list.add(customItem.getLocalizedName());
            }
            return list;
        }else return null;
    }
}

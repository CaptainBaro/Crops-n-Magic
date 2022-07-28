package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.spells.Spell;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CropCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if(args.length ==1){
                if(CustomItemBuilder.getCrop(args[0])!=null){
                    Player player = (Player) sender;
                    player.getInventory().addItem(new  CustomItemBuilder(CustomItemBuilder.getCrop(args[0])).createEatingCrop(CustomItemBuilder.getCrop(args[0])));
                }else {
                    sender.sendMessage("§cThis is not a valid crop.");
                }
            }else {
                sender.sendMessage("§cPlease use /crop <crop>");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if(args.length==1){
            for (Crop customItem : Crop.values()){
                list.add(customItem.getLocalized_Eat());
            }
            return list;
        }else return null;
    }
}

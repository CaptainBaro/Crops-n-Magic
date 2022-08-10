package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.custom_crafting.WandUpgradeModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UpgradeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            if (args.length==1){
                if (CustomItemBuilder.getWandUpgrade(args[0])!=null){
                    Player player = (Player) sender;
                    player.getInventory().addItem(new CustomItemBuilder(CustomItemBuilder.getWandUpgrade(args[0])).createUpgradeModule());
                }
            }else {
                sender.sendMessage("§cPlease use /upgrademodule <upgrade_module>");
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if(args.length==1){
            for (WandUpgradeModule customItem : WandUpgradeModule.values()){
                list.add(customItem.getId());
            }
            return list;
        }else return null;

    }
}

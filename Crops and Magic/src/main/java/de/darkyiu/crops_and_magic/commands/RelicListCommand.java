package de.darkyiu.crops_and_magic.commands;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.relics.Relic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RelicListCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length!=2){
            sender.sendMessage("§cPlease use /reliclist <add|remove> <relic>");
            return false;
        }
        if (args[0].equalsIgnoreCase("add")){
            if (Main.getPlugin().getRelicList().contains(args[1])){
                sender.sendMessage("§cThis relic is already on the list.");
                return false;
            }
            if (getRelic(args[1])==null){
                sender.sendMessage("§cThat is not a relic.");
            }
            Main.getPlugin().getRelicList().add(getRelic(args[1]).toString());
            sender.sendMessage("§aThe relic got added to the list. It won't be able to obtain till it gets removed.");
            return false;
        }
        if (args[0].equalsIgnoreCase("remove")){
            if (!Main.getPlugin().getRelicList().contains(args[1])){
                sender.sendMessage("§cThis relic isn't on the list.");
                return false;
            }
            Main.getPlugin().getRelicList().remove(args[1]);
            sender.sendMessage("§aThe relic got removed from the list. It's now available to obtain.");
            return false;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if (args.length == 1){
            list.add("add");
            list.add("remove");
            return list;
        }
        if(args.length==2){
            if (args[0].equalsIgnoreCase("remove")){
                for (String string : Main.getPlugin().getRelicList()){
                    list.add(string);
                    return list;
                }
            }
            for (Relic customItem : Relic.values()){
                list.add(customItem.toString());
            }
            return list;
        }
        return null;
    }

    public Relic getRelic(String name){
        for (Relic relic : Relic.values()){
            if (relic.toString().equals(name)){
                return relic;
            }
        }
        return null;
    }

}

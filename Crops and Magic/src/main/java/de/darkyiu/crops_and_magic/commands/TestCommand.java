package de.darkyiu.crops_and_magic.commands;


import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.wand.WandAssemblyTable;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;

import javax.annotation.Nonnull;
import java.util.Map;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,@Nonnull String s,@Nonnull String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            player.discoverRecipe(NamespacedKey.fromString("wand_core"));
            player.openInventory(WandAssemblyTable.createGui(WandAssemblyTable.title_first));


        }
        return false;
    }
}

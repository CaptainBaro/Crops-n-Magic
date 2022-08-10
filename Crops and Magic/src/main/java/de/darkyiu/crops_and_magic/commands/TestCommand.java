package de.darkyiu.crops_and_magic.commands;


import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftInventory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTables;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import java.util.Map;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,@Nonnull String s,@Nonnull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

        }
        return false;
    }
}

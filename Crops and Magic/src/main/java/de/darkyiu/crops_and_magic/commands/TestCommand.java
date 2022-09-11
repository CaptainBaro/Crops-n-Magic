package de.darkyiu.crops_and_magic.commands;


import de.darkyiu.crops_and_magic.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftInventory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.ItemStack;
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
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            player.getInventory().setItemInMainHand(new ItemBuilder(Material.TOTEM_OF_UNDYING).setModelData(5).build());
            player.playEffect(EntityEffect.TOTEM_RESURRECT);
            player.getInventory().setItemInMainHand(itemStack);
        }
        return false;
    }
}

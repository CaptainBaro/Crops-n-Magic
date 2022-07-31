package de.darkyiu.crops_and_magic.util;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BasicUtility {

    public static boolean giveItemSavely(HumanEntity player, ItemStack itemStack){
        if (player.getInventory().firstEmpty()==-1){
            player.getWorld().dropItem(player.getLocation(), itemStack);
            return false;
        }else {
            player.getInventory().addItem(itemStack);
            return true;
        }
    }

    public static boolean healPlayerSavely(Player player, int amount){
        if ((player.getHealth() + amount) > 20){
            player.setHealth(20);
            return false;
        }else {
            player.setHealth(player.getHealth()+amount);
            return true;
        }
    }


}

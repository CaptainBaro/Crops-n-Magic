package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.custom_crafting.CustomFood;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EatListener implements Listener {

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (event.getItem().getItemMeta()==null)return;
        if (CustomItemBuilder.getCustomFood(event.getItem())!=null) {
            event.setCancelled(true);
            CustomFood customFood = CustomItemBuilder.getCustomFood(event.getItem());
            Player player = event.getPlayer();
            if (event.getItem().getType().equals(Material.BEETROOT_SOUP)){
                event.getItem().setAmount(event.getItem().getAmount()-1);
                player.getInventory().addItem(new ItemStack(Material.BOWL));
            }
            event.getItem().setAmount(event.getItem().getAmount()-1);
            assert customFood != null;
            if ((player.getFoodLevel() + customFood.getFood()) > 20){
                player.setFoodLevel(20);
            }else {
                player.setFoodLevel(player.getFoodLevel() + customFood.getFood());
            }
            player.setSaturation(player.getSaturation() + customFood.getSaturation());
            if (customFood.getEffect()!=null){
                double r = Math.random();
                if (r < customFood.getChance()){
                    player.addPotionEffect(customFood.getEffect());
                }
            }
        }
    }
}

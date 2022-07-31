package de.darkyiu.crops_and_magic.wand;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SwapItemListener implements Listener {

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if (event.getOffHandItem() == null)return;
        if (event.getOffHandItem().getItemMeta()==null)return;
        if (event.getOffHandItem().getItemMeta().getLocalizedName().contains("Item.Wand.")){
            Main.getPlugin().getWandSpellHashmap().setSpells(event.getOffHandItem().getItemMeta().getLocalizedName(), Main.getPlugin().getWandSpellHashmap().getSpell(event.getOffHandItem().getItemMeta().getLocalizedName()) + 1);
            event.getPlayer().sendMessage("§fYou changed your spell slot.");
            event.setCancelled(true);
        }

    }
}

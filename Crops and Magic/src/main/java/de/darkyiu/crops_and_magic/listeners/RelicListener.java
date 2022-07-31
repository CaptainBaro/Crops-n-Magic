package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RelicListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem()==null || event.getItem().getItemMeta()==null)return;
        if (CustomItemBuilder.getRelic(event.getItem().getItemMeta().getLocalizedName())!=null){
            Relic relic = CustomItemBuilder.getRelic(event.getItem().getItemMeta().getLocalizedName());
            event.setCancelled(true);
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
                assert relic != null;
                relic.getRelicAbility().onRightClick(event.getPlayer(), relic);
            }else if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                assert relic != null;
                relic.getRelicAbility().onLeftClick(event.getPlayer(), relic);
            }
        }

    }
}

package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.spells.Spell;
import de.darkyiu.crops_and_magic.wand.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.TimeUnit;

public class RelicListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem()==null || event.getItem().getItemMeta()==null)return;
        if (CustomItemBuilder.getRelic(event.getItem().getItemMeta().getLocalizedName())!=null){
            Relic relic = CustomItemBuilder.getRelic(event.getItem().getItemMeta().getLocalizedName());
            event.setCancelled(true);
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
                assert relic != null;
                if (onCooldown(event.getPlayer(), relic)){
                    relic.getRelicAbility().onRightClick(event.getPlayer(), relic);
                }
            }else if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                assert relic != null;
                if (onCooldown(event.getPlayer(), relic)){
                    relic.getRelicAbility().onLeftClick(event.getPlayer(), relic);
                }
            }
        }

    }

    public static boolean onCooldown(Player player, Relic relic){
        String playerItem = player.getUniqueId().toString() + relic.getLocalizedName();

        long timeleft = System.currentTimeMillis() - Main.getPlugin().getCooldownManager().getCooldown(playerItem);
        if (TimeUnit.MILLISECONDS.toSeconds(timeleft) >= relic.getCooldDown()){
            Main.getPlugin().getCooldownManager().setCooldown(playerItem, System.currentTimeMillis());
            return true;
        }else {
            long cooldown = (TimeUnit.MILLISECONDS.toSeconds(timeleft) - relic.getCooldDown());
            player.sendMessage("§cThis relic is still charging.");
            return false;
        }
    }
}

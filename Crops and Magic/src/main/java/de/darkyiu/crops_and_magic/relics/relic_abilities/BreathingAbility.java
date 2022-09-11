package de.darkyiu.crops_and_magic.relics.relic_abilities;


import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Objects;

public class BreathingAbility implements RelicAbility, Listener {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {

    }

    @Override
    public void onEvent(EntityDeathEvent event) {

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))return;
        Player player = (Player) event.getEntity();
        if (player.getInventory().contains(new CustomItemBuilder(Relic.UNDERWATER_BREATHING_RELIC).createRelic())){
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.DROWNING))return;
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (!event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH))return;
        if (Objects.requireNonNull(event.getCaught()).getType().equals(EntityType.DROPPED_ITEM)){
            double r = Math.random();
            if (Main.getPlugin().getRelicList().contains(Relic.UNDERWATER_BREATHING_RELIC.toString())){
                return;
            }
            if (r<0.005){
                event.getCaught().remove();
                Item item = event.getPlayer().getWorld().dropItem(event.getCaught().getLocation(), new CustomItemBuilder(Relic.UNDERWATER_BREATHING_RELIC).createRelic());
                event.getHook().setHookedEntity(item);
                event.getHook().pullHookedEntity();
                Main.getPlugin().getRelicList().add(Relic.UNDERWATER_BREATHING_RELIC.toString());
                Main.getPlugin().data.addRelics(event.getPlayer().getUniqueId(), 1);
                Relic.relicFound();
            }
        }

    }
}

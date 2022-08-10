package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.listeners.RelicListener;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LastChanceRelic implements RelicAbility, Listener {
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
        if (player.getInventory().contains(new CustomItemBuilder(Relic.LAST_CHANCE_RELIC).createRelic())){
            if (player.getHealth()-event.getFinalDamage()<=4){
                if (!RelicListener.onCooldown(player, Relic.LAST_CHANCE_RELIC))return;
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*30, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10*30, 1));
            }
        }
    }
}

package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealingRelic implements RelicAbility, Listener {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        BasicUtility.healPlayerSavely(player, 12);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10*20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15*30, 1));
        Location location = player.getLocation().add(0,2.5,0);
        location.getWorld().spawnParticle(Particle.HEART, location, 10);
    }

    @Override
    @EventHandler
    public void onEvent(EntityDeathEvent event) {
        if (event.getEntity().getType().equals(EntityType.ZOMBIE)){
            if (event.getEntity().getKiller()==null)return;
            double r = Math.random();
            if (Main.getPlugin().getRelicList().contains(Relic.HEALING_RELIC.toString())){
                return;
            }
            if (r<0.01){
                event.getDrops().add(new CustomItemBuilder(Relic.HEALING_RELIC).createRelic());
                Main.getPlugin().getRelicList().add(Relic.HEALING_RELIC.toString());
                Relic.relicFound();
            }
        }
    }

}

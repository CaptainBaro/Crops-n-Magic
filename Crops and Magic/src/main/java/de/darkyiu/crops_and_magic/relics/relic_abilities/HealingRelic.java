package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealingRelic implements RelicAbility {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        player.setHealth(player.getHealth() + 12);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 25*30, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15*30, 1));
        Location location = player.getLocation().add(0,1,0);
        location.getWorld().spawnParticle(Particle.HEART, location, 10);
    }
}

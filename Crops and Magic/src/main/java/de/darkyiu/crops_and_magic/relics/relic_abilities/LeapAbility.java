package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LeapAbility implements RelicAbility,Listener {


    @Override
    public void onLeftClick(Player player, Relic relic) {


    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        player.setVelocity(player.getLocation().getDirection().multiply(10));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*10, 0));
    }

    @Override
    public void onEvent(EntityDeathEvent event) {

    }
}

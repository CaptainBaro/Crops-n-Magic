package de.darkyiu.crops_and_magic.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GreatHeal implements SpellAbility{
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        player.setHealth(player.getHealth() + 8);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10*20, 2));
        Location location = player.getLocation().add(0,1,0);
        location.getWorld().spawnParticle(Particle.HEART, location, 10);
    }


}

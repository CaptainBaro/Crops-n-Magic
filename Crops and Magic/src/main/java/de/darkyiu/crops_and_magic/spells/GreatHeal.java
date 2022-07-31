package de.darkyiu.crops_and_magic.spells;

import de.darkyiu.crops_and_magic.util.BasicUtility;
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
        BasicUtility.healPlayerSavely(player, 8);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*5, 0));
        Location location = player.getLocation().add(0,2.5,0);
        location.getWorld().spawnParticle(Particle.HEART, location, 10);
    }


}

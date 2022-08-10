package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Base64;

import static de.darkyiu.crops_and_magic.util.BasicUtility.getEntitiesByLocation;

public class FreezeLaser implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        BasicUtility.shootLaser(player, player.getEyeLocation(), 15, 2.5f, 6, Particle.SCRAPE, Sound.ENTITY_PLAYER_HURT_FREEZE,false, true, false);
    }

}

package de.darkyiu.crops_and_magic.spells.spell_abilities;


import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LaserSpell implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        BasicUtility.shootLaser(player,player.getEyeLocation(), 10, 2.5f, 5, Particle.VILLAGER_HAPPY, Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
    }
}

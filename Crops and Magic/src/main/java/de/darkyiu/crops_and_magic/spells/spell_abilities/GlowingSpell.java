package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import de.darkyiu.crops_and_magic.wand.SpellListener;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GlowingSpell implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        BasicUtility.shootLaser(player, player.getEyeLocation(), 15, 2.5f, SpellListener.calculateDamage(player, 5, itemStack.getItemMeta().getLocalizedName()), Particle.CRIT_MAGIC, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, false, false, true);
    }
}

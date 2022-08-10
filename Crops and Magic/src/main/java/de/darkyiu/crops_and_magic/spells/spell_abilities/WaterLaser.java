package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WaterLaser implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        if (player.isInWater()){
            BasicUtility.shootLaser(player,player.getEyeLocation(), 20, 2.5f, 10,Particle.WATER_BUBBLE, Sound.ENTITY_ITEM_PICKUP);
        }else {
            BasicUtility.shootLaser(player,player.getEyeLocation(), 20, 2.5f, 6, Particle.WATER_BUBBLE, Sound.ENTITY_ITEM_PICKUP);
        }
    }
}

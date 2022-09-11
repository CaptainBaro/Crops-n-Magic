package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import de.darkyiu.crops_and_magic.wand.SpellListener;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlazingLaser implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        if (player.getWorld().getName().contains("nether")){
            BasicUtility.shootLaser(player,player.getEyeLocation(), 30, 2.5f, SpellListener.calculateDamage(player,12, itemStack.getItemMeta().getLocalizedName()), Particle.FLAME, Sound.ITEM_BUCKET_FILL_LAVA,true, false, false);
        }else {
            BasicUtility.shootLaser(player,player.getEyeLocation(), 30, 2.5f, SpellListener.calculateDamage(player, 10, itemStack.getItemMeta().getLocalizedName()), Particle.FLAME, Sound.ITEM_BUCKET_FILL_LAVA, true, false, false);
        }
    }


}

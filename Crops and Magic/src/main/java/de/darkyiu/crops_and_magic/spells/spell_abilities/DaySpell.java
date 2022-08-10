package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DaySpell implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        for (long i = player.getWorld().getTime(); i<=7000; i=i+1000){
            player.getWorld().setTime(i);
        }
    }
}

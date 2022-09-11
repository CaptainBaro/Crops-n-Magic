package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.spells.Buff;
import de.darkyiu.crops_and_magic.spells.SpellAbility;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SamuraiBuff implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        Main.getPlugin().getBuffHashmap().addBuff(player, Buff.MORE_DAMAGE, Buff.MORE_DAMAGE.getBaseDuration(), 0);
    }
}

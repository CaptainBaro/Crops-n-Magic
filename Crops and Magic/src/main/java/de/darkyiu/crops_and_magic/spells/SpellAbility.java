package de.darkyiu.crops_and_magic.spells;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface SpellAbility {

    public void onLeftClick(Player player, ItemStack itemStack);

    public void onRightCLick(Player player, ItemStack itemStack);

}

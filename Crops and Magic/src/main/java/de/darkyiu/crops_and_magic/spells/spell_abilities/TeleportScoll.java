package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeleportScoll implements SpellAbility {

    private int distance = 8;

    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        BasicUtility.teleportPlayer(player, distance);
    }
}

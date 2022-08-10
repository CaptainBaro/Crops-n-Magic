package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BasicCleaning implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        if (player.getActivePotionEffects().isEmpty())return;
        List<PotionEffect> potionEffects = new ArrayList<>(player.getActivePotionEffects());
        player.removePotionEffect(potionEffects.get(new Random().nextInt(potionEffects.size())).getType());
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 0.1f, 2);
    }
}

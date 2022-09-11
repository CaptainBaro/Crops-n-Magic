package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.spells.Spell;
import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EnderianTeleport implements SpellAbility, Listener {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        BasicUtility.teleportPlayer(player, 30);
    }

    @EventHandler
    public void onEvent(EntityDeathEvent event) {
        if (event.getEntity().getType().equals(EntityType.ENDERMAN)){
            if (event.getEntity().getKiller()==null)return;
            double r = Math.random();
            if (r>0.01)return;
            event.getDrops().add(new CustomItemBuilder(Spell.GREAT_TELEPORT).createSpell());
        }
    }
}

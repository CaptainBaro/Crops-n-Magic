package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Objects;

public class MountSpell implements SpellAbility, Listener {


    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
        player.playSound(player, Sound.BLOCK_ROOTED_DIRT_BREAK, 3, 3);
        player.getWorld().spawnParticle(Particle.SPELL_MOB, player.getLocation().add(0, 1,0), 5);
        horse.setTamed(true);
        horse.setOwner(player);
        horse.addPassenger(player);
        horse.setCustomName(player.getName() + "'s Mount");
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setCustomNameVisible(true);
    }

    @EventHandler
    public void onSneak(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (event.getDismounted().getType() != EntityType.HORSE) return;
        Horse horse = (Horse) event.getDismounted();
        if (horse.getCustomName() == null) return;
        if (Objects.equals(horse.getCustomName(), player.getName() + "'s Mount")) {
            horse.getInventory().setSaddle(new ItemStack(Material.AIR));
            horse.remove();
        }
    }
}

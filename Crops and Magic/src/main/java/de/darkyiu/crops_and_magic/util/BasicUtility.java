package de.darkyiu.crops_and_magic.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasicUtility {

    public static boolean giveItemSavely(HumanEntity player, ItemStack itemStack){
        if (player.getInventory().firstEmpty()==-1){
            player.getWorld().dropItem(player.getLocation(), itemStack);
            return false;
        }else {
            player.getInventory().addItem(itemStack);
            return true;
        }
    }

    public static boolean healPlayerSavely(Player player, int amount){
        if ((player.getHealth() + amount) > 20){
            player.setHealth(20);
            return false;
        }else {
            player.setHealth(player.getHealth()+amount);
            return true;
        }
    }

    public static void teleportPlayer(Player player, int blocks){
        Block block = player.getTargetBlock((Set<Material>) null, blocks);
        Location location = block.getLocation();
        float pitch = player.getEyeLocation().getPitch();
        float yaw = player.getEyeLocation().getYaw();
        location.setYaw(yaw);
        location.setPitch(pitch);
        location.add(0,1,0);
        player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 5, 5);
    }

    public static List<Entity >getNearbyLivingEntitiesExceptEntity(Entity entity, int x, int y, int z){
        List<Entity> entities = new ArrayList<>();
        for (Entity e : entity.getNearbyEntities(x, y, z)){
            if (!e.getUniqueId().equals(entity.getUniqueId())){
                entities.add(e);
            }
        }
        return entities;
    }

    public static List<Entity> getEntitiesByLocation(Location location, float r){
        List<Entity> ent = new ArrayList<>();
        for (Entity e : location.getWorld().getEntities()){
            if (e.getBoundingBox().contains(location.getX(), location.getY(), location.getZ())){
                ent.add(e);
            }
        }
        return ent;
    }

    public static void shootLaser(Entity shooter,Location location, int range, float with, double damage ,Particle particle, Sound sound){
        Location loc = location;
        for (int i = 0; i <= range; i++){
            loc = loc.add(loc.getDirection().getX(), loc.getDirection().getY() - 0.005, loc.getDirection().getZ());
            loc.getWorld().spawnParticle(particle, loc, 1);
            for (Entity ent : getEntitiesByLocation(loc, with)){
                if (ent.getUniqueId().equals(shooter.getUniqueId())){

                }else if (ent instanceof LivingEntity){
                    ((LivingEntity)ent).damage(damage, shooter);
                }
            }
            if (loc.getBlock().getType().isSolid()){
                break;
            }
            location.getWorld().playSound(location, sound, 1F, 0.5f);
        }
    }
    public static void shootLaser(Entity shooter,Location location, int range, float with, double damage ,Particle particle, Sound sound,boolean flammable, boolean freezing, boolean glow){
        Location loc = location;
        for (int i = 0; i <= range; i++){
            loc = loc.add(loc.getDirection().getX(), loc.getDirection().getY() - 0.005, loc.getDirection().getZ());
            loc.getWorld().spawnParticle(particle, loc, 1);
            for (Entity ent : getEntitiesByLocation(loc, with)){
                if (ent.getUniqueId().equals(shooter.getUniqueId())){

                } else if (ent instanceof LivingEntity){
                    ((LivingEntity)ent).damage(damage,shooter);
                    if (flammable){
                        ((LivingEntity)ent).setFireTicks(100);
                    }
                    if (freezing){
                        ((LivingEntity)ent).setFreezeTicks(100);
                    }
                    if (glow){
                        ((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20*5, 0));
                    }
                }
            }
            if (loc.getBlock().getType().isSolid()){
                break;
            }
            location.getWorld().playSound(location, sound, 1F, 0.5f);
        }
    }



}

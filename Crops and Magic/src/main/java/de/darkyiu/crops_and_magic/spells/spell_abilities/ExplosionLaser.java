package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static de.darkyiu.crops_and_magic.util.BasicUtility.getEntitiesByLocation;

public class ExplosionLaser implements SpellAbility {
    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        shootFreezeLaser(player, player.getEyeLocation(), 30, 2.5f, 8, Particle.EXPLOSION_NORMAL, Sound.ENTITY_PANDA_BITE);
    }

    public static void shootFreezeLaser(Entity shooter, Location location, int range, float with, double damage , Particle particle, Sound sound){
        Location loc = location;
        for (int i = 0; i <= range; i++){
            loc = loc.add(loc.getDirection().getX(), loc.getDirection().getY() - 0.005, loc.getDirection().getZ());
            loc.getWorld().spawnParticle(particle, loc, 1);
            for (Entity ent : getEntitiesByLocation(loc, with)){
                if (ent.getUniqueId().equals(shooter.getUniqueId())){

                }else if (ent instanceof LivingEntity){
                    ((LivingEntity)ent).damage(damage, shooter);
                    ent.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 10);
                    for (Entity entities : ent.getNearbyEntities(2, 2, 2)){
                        if (entities instanceof LivingEntity){
                            ((LivingEntity) entities).damage(6, shooter);
                        }
                    }
                    ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 3, 3);
                    break;
                }
            }
            if (loc.getBlock().getType().isSolid()){
                break;
            }
            location.getWorld().playSound(location, sound, 1F, 0.5f);
        }
    }
}

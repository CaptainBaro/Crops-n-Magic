package de.darkyiu.crops_and_magic.spells.spell_abilities;

import de.darkyiu.crops_and_magic.spells.SpellAbility;
import de.darkyiu.crops_and_magic.wand.SpellListener;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.Objects;

public class LightningAbility implements SpellAbility {


    @Override
    public void onLeftClick(Player player, ItemStack itemStack) {

    }

    @Override
    public void onRightCLick(Player player, ItemStack itemStack) {
        if (player.getTargetBlockExact(40)==null)return;
        Location location = Objects.requireNonNull(player.getTargetBlockExact(40)).getLocation();
        player.getWorld().strikeLightningEffect(location);
        BoundingBox boundingBox = new BoundingBox();
        for (Entity entity : location.getWorld().getNearbyEntities(location,2, 3, 2)){
            if (entity.getUniqueId() != player.getUniqueId()){
                if (entity.getType().isAlive()){
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.damage(SpellListener.calculateDamage(player,8, itemStack.getItemMeta().getLocalizedName()), player);
                }
            }
        }
    }
}

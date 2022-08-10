package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GlowingRelic implements RelicAbility {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        for (Entity entity : player.getWorld().getEntities()){
            if (entity instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20*10, 0, false));
            }
        }
    }

    @Override
    public void onEvent(EntityDeathEvent event) {
        if (event.getEntity().getType().equals(EntityType.PHANTOM)){
            if (event.getEntity().getKiller()==null)return;
            double r = Math.random();
            if (Main.getPlugin().getRelicList().contains(Relic.GLOWING_RELIC.toString())){
                return;
            }
            if (r<0.01){
                event.getDrops().add(new CustomItemBuilder(Relic.GLOWING_RELIC).createRelic());
                Main.getPlugin().getRelicList().add(Relic.GLOWING_RELIC.toString());
                Relic.relicFound();
            }
        }
    }
}

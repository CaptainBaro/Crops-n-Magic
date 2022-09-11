package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FearRelic implements RelicAbility, Listener {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        for (Entity entity : BasicUtility.getNearbyLivingEntitiesExceptEntity(player, 6, 6, 6)){
            if (!entity.isDead()){
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20*60, 0));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*60, 0));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*10, 0));
            }
        }
    }

    @Override
    public void onEvent(EntityDeathEvent event) {
        if (event.getEntity().getType().equals(EntityType.WARDEN)){
            if (event.getEntity().getKiller()==null)return;
            double r = Math.random();
            if (Main.getPlugin().getRelicList().contains(Relic.FEAR_RELIC.toString())){
                return;
            }
            if (r<0.3){
                event.getDrops().add(new CustomItemBuilder(Relic.FEAR_RELIC).createRelic());
                Main.getPlugin().getRelicList().add(Relic.FEAR_RELIC.toString());
                Relic.relicFound();
                Main.getPlugin().data.addRelics(event.getEntity().getKiller().getUniqueId(), 1);
            }
        }
    }
}

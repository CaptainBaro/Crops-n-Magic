package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

public class GrowthRelic implements RelicAbility {


    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        for (Entity entity : player.getNearbyEntities(5,5,5)){
            if (entity.getType().equals(EntityType.ITEM_FRAME)){
                ItemFrame itemFrame = (ItemFrame) entity;
                if (itemFrame.getItem().getItemMeta()==null)return;
                if (CustomItemBuilder.getFarmingCrop(itemFrame.getItem())!=null){
                    Crop crop = CustomItemBuilder.getFarmingCrop(itemFrame.getItem());
                    if (CustomItemBuilder.getStage(itemFrame.getItem())>2){
                        itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 3));
                    }else {
                        itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, CustomItemBuilder.getStage(itemFrame.getItem())+1));
                    }
                }
            }
        }
    }

    @Override
    public void onEvent(EntityDeathEvent event) {

    }
}

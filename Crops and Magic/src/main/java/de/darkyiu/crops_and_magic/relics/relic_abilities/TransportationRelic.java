package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class TransportationRelic implements RelicAbility {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        player.openInventory(player.getEnderChest());
    }

    @Override
    public void onEvent(EntityDeathEvent event) {
        if (event.getEntity().getType().equals(EntityType.ENDERMAN)){
            if (event.getEntity().getKiller()==null)return;
            double r = Math.random();
            if (Main.getPlugin().getRelicList().contains(Relic.TRANSPORTATION_RELIC.toString())){
                return;
            }
            if (r<0.002){
                event.getDrops().add(new CustomItemBuilder(Relic.TRANSPORTATION_RELIC).createRelic());
                Main.getPlugin().getRelicList().add(Relic.TRANSPORTATION_RELIC.toString());
                Relic.relicFound();
                Main.getPlugin().data.addRelics(event.getEntity().getKiller().getUniqueId(), 1);
            }
        }
    }

}

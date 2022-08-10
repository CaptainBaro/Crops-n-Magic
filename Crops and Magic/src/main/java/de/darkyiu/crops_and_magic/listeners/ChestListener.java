package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.spells.Spell;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChestListener implements Listener {

    private List<Crop> crops = new ArrayList<>();
    private boolean sucess = false;
    private List<Spell> spells = new ArrayList<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock()==null)return;
        if (!event.getClickedBlock().getType().equals(Material.CHEST))return;
        org.bukkit.block.Chest chest = (Chest) event.getClickedBlock().getState();
        if (chest.getLootTable()==null)return;
        LootTable lootTable = chest.getLootTable();
        if (lootTable.getKey().toString().contains("minecraft:chests/village")){
            if (new Random().nextInt(150)==100){
                if (!Main.getPlugin().getRelicList().contains(Relic.GROWTH_RELIC.toString())){
                    chest.getBlockInventory().addItem(new CustomItemBuilder(Relic.GROWTH_RELIC).createRelic());
                    Main.getPlugin().getRelicList().add(Relic.GROWTH_RELIC.toString());
                    Relic.relicFound();
                }
            }
            crops.addAll(Arrays.asList(Crop.values()));
            Crop crop = crops.get(new Random().nextInt(crops.size()));
            chest.getBlockInventory().addItem(new CustomItemBuilder(crop).createEatingCrop(crop, new Random().nextInt(5)));
        }else if (lootTable.equals(LootTables.DESERT_PYRAMID.getLootTable())|| lootTable.equals(LootTables.RUINED_PORTAL.getLootTable())|| lootTable.equals(LootTables.JUNGLE_TEMPLE.getLootTable())|| lootTable.getKey().toString().contains("minecraft:chests/shipwreck")){
            generateSpells(chest, 2,1, 0.3);
        } else if (lootTable.equals(LootTables.IGLOO_CHEST.getLootTable()) || lootTable.equals(LootTables.BURIED_TREASURE.getLootTable())|| lootTable.equals(LootTables.PILLAGER_OUTPOST.getLootTable())) {
            generateSpells(chest, 2, 2, 1);
        }else if (lootTable.equals(LootTables.SIMPLE_DUNGEON.getLootTable())|| lootTable.equals(LootTables.NETHER_BRIDGE.getLootTable())){
            generateSpells(chest, 3, 2, 0.6);
        }else if (lootTable.equals(LootTables.STRONGHOLD_CORRIDOR.getLootTable())||lootTable.equals(LootTables.STRONGHOLD_CROSSING.getLootTable())|| lootTable.equals(LootTables.END_CITY_TREASURE.getLootTable())){
            generateSpells(chest, 4, 3, 0.3);
        }else if (lootTable.equals(LootTables.STRONGHOLD_LIBRARY.getLootTable())){
            generateSpells(chest, 4,4, 1);
        }else if (lootTable.getKey().toString().contains("minecraft:chests/bastion")|| lootTable.equals(LootTables.ANCIENT_CITY.getLootTable()) || lootTable.equals(LootTables.WOODLAND_MANSION.getLootTable())){
            generateSpells(chest, 5, 4, 0.3);
        }

    }

    private void generateSpells(Chest chest, int highestTier, int lowestTier,double probability) {
        sucess=false;
        double r = Math.random();
        if (!(r<probability))return;
        while (!sucess){
            spells = new ArrayList<>();
            spells.addAll(Arrays.asList(Spell.values()));
            Spell spell = spells.get(new Random().nextInt(spells.size()));
            if ((spell.getTier()>=lowestTier&&spell.getTier()<=highestTier)) {
                double r1 = Math.random();
                if (r1 > 0.3) {
                    if (spell.getTier() == lowestTier) {
                        chest.getBlockInventory().addItem(new CustomItemBuilder(spell).createSpell());
                        sucess = true;
                    }
                } else {
                    if (spell.getTier() == highestTier) {
                        chest.getBlockInventory().addItem(new CustomItemBuilder(spell).createSpell());
                        sucess = true;
                    }
                }
            }
        }
    }

}

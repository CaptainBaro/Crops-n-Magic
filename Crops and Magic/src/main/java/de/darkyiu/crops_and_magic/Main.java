package de.darkyiu.crops_and_magic;

import com.sun.jna.platform.win32.Winspool;
import de.darkyiu.crops_and_magic.commands.*;
import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.listeners.*;
import de.darkyiu.crops_and_magic.relics.relic_abilities.HealingRelic;
import de.darkyiu.crops_and_magic.util.Config;

import de.darkyiu.crops_and_magic.wand.SpellListener;
import de.darkyiu.crops_and_magic.wand.SwapItemListener;
import de.darkyiu.crops_and_magic.wand.WandAssemblyTable;
import de.darkyiu.crops_and_magic.wand.WandSpellHashmap;
import junit.framework.TestListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private List<String> frameList;
    private List<String> relicList;
    private Config config;
    private Config wandFile;
    private ItemManager itemManager;
    private WandSpellHashmap wandSpellHashmap;

    @Override
    public void onEnable() {
        plugin = this;
        itemManager = new ItemManager();
        wandSpellHashmap = new WandSpellHashmap();
        frameList = new ArrayList<>();
        relicList = new ArrayList<>();

        config = new Config("crop.yml", getDataFolder());
        wandFile = new Config("wands.yml", getDataFolder());
        try {
            frameList = config.getConfiguration().getStringList("framelist");
        }catch (Exception e){
            config.set("framelist", frameList);
            config.save();
            Bukkit.getLogger().info("Neue Framelist created");
        }
        try {
            relicList = config.getConfiguration().getStringList("reliclist");
        }catch (Exception e){
            config.set("reliclist", relicList);
            config.save();
            Bukkit.getLogger().info("Neue Reliclist created");
        }
        Bukkit.getPluginManager().registerEvents(new TeatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlantListener(), this);
        Bukkit.getPluginManager().registerEvents(new RelicListener(), this);
        Bukkit.getPluginManager().registerEvents(new EatListener(), this);
        Bukkit.getPluginManager().registerEvents(new HealingRelic(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WandAssemblyTable(), this);
        Bukkit.getPluginManager().registerEvents(new SwapItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpellListener(), this);
        Bukkit.getPluginManager().registerEvents(new WandListener(), this);

        getCommand("relic").setExecutor(new RelicCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("spell").setExecutor(new SpellCommand());
        getCommand("cui").setExecutor(new CustomItemCommand());
        getCommand("crop").setExecutor(new CropCommand());

        itemManager.init();
        growItemFrames();
        Bukkit.getLogger().info("Plugin loaded");


    }

    @Override
    public void onDisable() {
        config.set("framelist", frameList);
        config.set("reliclist", relicList);
        config.save();
    }

    public static Main getPlugin() {
        return plugin;
    }



    public List<String> getFrameList() {
        return frameList;
    }

    public List<String> getRelicList() {
        return relicList;
    }
    public Config getCropConfig() {
        return config;
    }

    public void growItemFrames(){
        for (String uuid : frameList){
            Entity entity = Bukkit.getEntity(UUID.fromString(uuid));
            if (entity!=null && entity.getType().equals(EntityType.ITEM_FRAME)){
                ItemFrame itemFrame = (ItemFrame) entity;
                Crop crop = CustomItemBuilder.getFarmingCrop(itemFrame.getItem());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (CustomItemBuilder.getStage(itemFrame.getItem())>2){
                            itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 3));
                        }else {
                            itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, CustomItemBuilder.getStage(itemFrame.getItem())+1));
                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (CustomItemBuilder.getStage(itemFrame.getItem())>2){
                                    itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 3));
                                }else {
                                    itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, CustomItemBuilder.getStage(itemFrame.getItem())+1));
                                }
                            }
                        }.runTaskLater(Main.getPlugin(), 20*60*5);
                    }
                }.runTaskLater(Main.getPlugin(),20*60*5);
            }
        }
    }

    public Config getWandFile() {
        return wandFile;
    }

    public WandSpellHashmap getWandSpellHashmap() {
        return wandSpellHashmap;
    }
}

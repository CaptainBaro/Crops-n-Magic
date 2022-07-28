package de.darkyiu.crops_and_magic;

import de.darkyiu.crops_and_magic.commands.CropCommand;
import de.darkyiu.crops_and_magic.commands.SpellCommand;
import de.darkyiu.crops_and_magic.commands.TestCommand;
import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.listeners.PlantListener;
import de.darkyiu.crops_and_magic.listeners.TeatListener;
import de.darkyiu.crops_and_magic.util.Config;
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
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private List<String> frameList;
    private Config config;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        plugin = this;
        itemManager = new ItemManager();
        frameList = new ArrayList<>();

        config = new Config("crop.yml", getDataFolder());
        try {
            frameList = config.getConfiguration().getStringList("framelist");
        }catch (Exception e){
            config.set("framelist", frameList);
            config.save();
            Bukkit.getLogger().info("Neue Framelist created");
        }
        Bukkit.getPluginManager().registerEvents(new TeatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlantListener(), this);

        getCommand("test").setExecutor(new TestCommand());
        getCommand("spell").setExecutor(new SpellCommand());
        getCommand("crop").setExecutor(new CropCommand());

        itemManager.init();
        growItemFrames();
        Bukkit.getLogger().info("Plugin loaded");


    }

    @Override
    public void onDisable() {
        config.set("framelist", frameList);
        config.save();
    }

    public static Main getPlugin() {
        return plugin;
    }



    public List<String> getFrameList() {
        return frameList;
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
                        }.runTaskLater(Main.getPlugin(), 20*10);
                    }
                }.runTaskLater(Main.getPlugin(),20*10);
            }
        }
    }
}

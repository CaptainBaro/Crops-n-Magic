package de.darkyiu.crops_and_magic;

import de.darkyiu.crops_and_magic.commands.*;
import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.listeners.*;
import de.darkyiu.crops_and_magic.relics.relic_abilities.BreathingAbility;
import de.darkyiu.crops_and_magic.relics.relic_abilities.FearRelic;
import de.darkyiu.crops_and_magic.relics.relic_abilities.HealingRelic;
import de.darkyiu.crops_and_magic.relics.relic_abilities.LastChanceRelic;
import de.darkyiu.crops_and_magic.spells.spell_abilities.EnderianTeleport;
import de.darkyiu.crops_and_magic.spells.spell_abilities.MountSpell;
import de.darkyiu.crops_and_magic.util.Config;

import de.darkyiu.crops_and_magic.wand.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private List<String> frameList;
    private List<String> relicList;
    private List<String> blockList;
    private Config config;
    private Config blockFile;
    private Config wandFile;
    private ItemManager itemManager;
    private WandSpellHashmap wandSpellHashmap;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        plugin = this;
        itemManager = new ItemManager();
        wandSpellHashmap = new WandSpellHashmap();
        cooldownManager = new CooldownManager();
        frameList = new ArrayList<>();
        blockList = new ArrayList<>();
        relicList = new ArrayList<>();

        blockFile = new Config("blocks.yml", getDataFolder());
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
        if (blockFile.getConfiguration().getString("blocklist")!=null){
            blockList = blockFile.getConfiguration().getStringList("blocklist");
        }else{
            blockFile.set("blocklist", blockList);
            blockFile.save();
            Bukkit.getLogger().info("Neue Blocklist created");
        }
        Bukkit.getPluginManager().registerEvents(new TeatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlantListener(), this);
        Bukkit.getPluginManager().registerEvents(new RelicListener(), this);
        Bukkit.getPluginManager().registerEvents(new EatListener(), this);
        Bukkit.getPluginManager().registerEvents(new HealingRelic(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BreathingAbility(), this);
        Bukkit.getPluginManager().registerEvents(new WandAssemblyTable(), this);
        Bukkit.getPluginManager().registerEvents(new SwapItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingTable(), this);
        Bukkit.getPluginManager().registerEvents(new SpellListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnderianTeleport(), this);
        Bukkit.getPluginManager().registerEvents(new FearRelic(), this);
        Bukkit.getPluginManager().registerEvents(new LastChanceRelic(), this);
        Bukkit.getPluginManager().registerEvents(new ChestListener(), this);
        Bukkit.getPluginManager().registerEvents(new MountSpell(), this);
        Bukkit.getPluginManager().registerEvents(new WandListener(), this);

        getCommand("upgrademodule").setExecutor(new UpgradeCommand());
        getCommand("relic").setExecutor(new RelicCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("spell").setExecutor(new SpellCommand());
        getCommand("cui").setExecutor(new CustomItemCommand());
        getCommand("crop").setExecutor(new CropCommand());

        itemManager.init();
        growItemFrames();
        forEveryPlayer();
        blockScheduler();
        Bukkit.getLogger().info("Plugin loaded");


    }

    @Override
    public void onDisable() {
        config.set("framelist", frameList);
        config.set("reliclist", relicList);
        blockFile.set("blocklist", blockList);
        config.save();
        blockFile.save();
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

    public void forEveryPlayer(){
        for (Player player : Bukkit.getOnlinePlayers()){
             for (NamespacedKey key : ItemManager.recipes){
                 player.discoverRecipe(key);
             }
        }
    }

    public void blockScheduler(){
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    for (String uuid : blockList){
                        Entity entity = Bukkit.getEntity(UUID.fromString(uuid));
                        if (entity!=null && entity.getType().equals(EntityType.ITEM_FRAME)){
                            ItemFrame itemFrame = (ItemFrame) entity;
                            if (itemFrame.getWorld().getBlockAt(itemFrame.getLocation()).getType()!= Material.GLASS){
                                BlockPlaceEvent.removeItemFrame(itemFrame);
                            }
                        }
                    }
                }catch (Exception e){

                }
            }
        }.runTaskTimer(this,  0, 5);

    }

    public Config getWandFile() {
        return wandFile;
    }

    public List<String> getBlockList() {
        return blockList;
    }

    public WandSpellHashmap getWandSpellHashmap() {
        return wandSpellHashmap;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public Config getBlockFile() {
        return blockFile;
    }
}

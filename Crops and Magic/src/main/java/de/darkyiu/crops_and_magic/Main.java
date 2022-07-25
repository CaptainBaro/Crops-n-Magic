package de.darkyiu.crops_and_magic;

import de.darkyiu.crops_and_magic.commands.TestCommand;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private ItemManager itemManager;

    @Override
    public void onEnable() {
        itemManager = new ItemManager();

        Bukkit.getLogger().info("Plugin loaded");
        getCommand("test").setExecutor(new TestCommand());
        itemManager.init();

    }

    @Override
    public void onDisable() {

    }


}

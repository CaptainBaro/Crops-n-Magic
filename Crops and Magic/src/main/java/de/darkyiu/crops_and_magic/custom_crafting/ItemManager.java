package de.darkyiu.crops_and_magic.custom_crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.nio.file.WatchKey;

public class ItemManager {

    public static ItemStack WandCore = new CustomItemBuilder(CustomItems.WAND_CORE).build();
    public static ItemStack Netherite_Core = new CustomItemBuilder(CustomItems.NETHERITE_CORE).build();

    public void init(){
        createWandCore();
        createNetheriteCore();
    }

    private void createWandCore(){
        ShapedRecipe shapelessRecipe = new ShapedRecipe(NamespacedKey.minecraft("wand_core"), WandCore);
        shapelessRecipe.shape("* *", "***", "* *");
        shapelessRecipe.setIngredient('*', Material.DIAMOND);
        Bukkit.getServer().addRecipe(shapelessRecipe);
    }

    private void createNetheriteCore(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("netherite_core"), Netherite_Core);
        shapedRecipe.shape("***", "*/*", "***");
        shapedRecipe.setIngredient('*', Material.NETHERITE_INGOT);
        shapedRecipe.setIngredient('/', Material.NETHER_STAR);
        Bukkit.getServer().addRecipe(shapedRecipe);
    }

}

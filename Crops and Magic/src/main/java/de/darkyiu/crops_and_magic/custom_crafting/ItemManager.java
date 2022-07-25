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
    public static ItemStack Wand;

    public void init(){
        createWandCore();
        createWand();
    }

    private void createWandCore(){
        ShapedRecipe shapelessRecipe = new ShapedRecipe(NamespacedKey.minecraft("wand_core"), WandCore);
        shapelessRecipe.shape("* *", "***", "* *");
        shapelessRecipe.setIngredient('*', Material.DIAMOND);
        Bukkit.getServer().addRecipe(shapelessRecipe);
    }
    private void createWand(){
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§aWand§c(Tier 1)");
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        Wand = itemStack;

        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("wand"), itemStack);
        shapedRecipe.shape(" * ", " / ", " # ");
        shapedRecipe.setIngredient('*', Material.STICK);
        shapedRecipe.setIngredient('/', Material.GOLD_INGOT);
        shapedRecipe.setIngredient('#', new RecipeChoice.ExactChoice(WandCore));
        Bukkit.addRecipe(shapedRecipe);
    }

}

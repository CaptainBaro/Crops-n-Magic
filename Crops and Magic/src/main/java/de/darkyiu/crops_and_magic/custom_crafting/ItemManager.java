package de.darkyiu.crops_and_magic.custom_crafting;

import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.relics.Relic;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.KeybindComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.nio.file.WatchKey;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack Tier_2_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_2).build();
    public static ItemStack Magical_Wood = new CustomItemBuilder(CustomItems.MAGICAL_WOOD).build();
    public static ItemStack Strawberry = new CustomItemBuilder(Crop.STRAWBERRY).createEatingCrop(Crop.STRAWBERRY);
    public static ItemStack Roasted_Strawberry = new CustomItemBuilder(CustomFood.ROASTED_STRAWBERRY).createFood();
    public static ItemStack Strawberry_Bowl = new CustomItemBuilder(CustomFood.STRAWBERRY_BOWL).createFood();
    public static ItemStack WandCore = new CustomItemBuilder(CustomItems.WAND_CORE).build();
    public static ItemStack Tier_1_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_1).build();
    public static ItemStack Tier_3_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_3).build();
    public static ItemStack Tier_4_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_4).build();
    public static ItemStack Tier_5_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_5).build();
    public static ItemStack Netherite_Core = new CustomItemBuilder(CustomItems.NETHERITE_CORE).build();
    public static ItemStack SPELL_TABLE = new CustomItemBuilder(CustomItems.SPELL_ASSEMBLE_BLOCK_ITEM).build();
    public static ItemStack Purified_Wand_Core = new CustomItemBuilder(CustomItems.PURIFIED_WAND_CORE).build();
    public static ItemStack Obsidian_Shaft = new CustomItemBuilder(CustomItems.OBSIDIAN_SHAFT).build();
    public static ItemStack[] tier_2_wand = new ItemStack[4];
    public static ItemStack[] tier_3_wand = new ItemStack[4];
    public static ItemStack[] tier_4_wand = new ItemStack[4];
    public static ItemStack[] tier_5_wand = new ItemStack[4];
    public static ItemStack[] obsidian_shaft = new ItemStack[10];

    public static List<NamespacedKey> recipes = new ArrayList<>();

    public void init(){
        initList();
        createWandCore();
        createNetheriteCore();
        createPurifiedWandCore();
        createStrawberryBowl();
        createRoastedStrawberry();
        createWandTier1();
        createMagicalWood();
        createSpellTable();
    }

    private void initList(){
        tier_2_wand[0] = Magical_Wood;
        tier_2_wand[1] = Tier_1_Wand;
        tier_2_wand[2] = new ItemStack(Material.GOLD_INGOT, 10);
        tier_2_wand[3] = Tier_2_Wand;

        tier_3_wand[0] = Purified_Wand_Core;
        tier_3_wand[1] = Tier_2_Wand;
        tier_3_wand[2] = new ItemStack(Material.GOLD_BLOCK, 3);
        tier_3_wand[3] = new CustomItemBuilder(CustomItems.WAND_TIER_3).build();

        tier_5_wand[0] = Netherite_Core;
        tier_5_wand[1] = Tier_4_Wand;
        tier_5_wand[2] = new ItemStack(Material.GOLD_BLOCK, 32);
        tier_5_wand[3] = Tier_5_Wand;

        tier_4_wand[0] = Obsidian_Shaft;
        tier_4_wand[1] = Tier_3_Wand;
        tier_4_wand[2] = new ItemStack(Material.IRON_BLOCK, 10);
        tier_4_wand[3] = Tier_4_Wand;

        obsidian_shaft[0] = new ItemStack(Material.OBSIDIAN, 20);
        obsidian_shaft[1] = new ItemStack(Material.AIR);
        obsidian_shaft[2] = new ItemStack(Material.AIR);
        obsidian_shaft[3] = new ItemStack(Material.AIR);
        obsidian_shaft[4] = new ItemStack(Material.OBSIDIAN, 20);
        obsidian_shaft[5] = new ItemStack(Material.AIR);
        obsidian_shaft[6] = new ItemStack(Material.AIR);
        obsidian_shaft[7] = new ItemStack(Material.AIR);
        obsidian_shaft[8] = new ItemStack(Material.OBSIDIAN, 20);
        obsidian_shaft[9] = Obsidian_Shaft;
    }

    private void createWandCore(){
        NamespacedKey key = NamespacedKey.minecraft("wand_core");
        ShapedRecipe shapelessRecipe = new ShapedRecipe(key, WandCore);
        shapelessRecipe.shape("* *", "***", "* *");
        shapelessRecipe.setIngredient('*', Material.EMERALD);
        Bukkit.getServer().addRecipe(shapelessRecipe);
        recipes.add(key);
    }


    private void createPurifiedWandCore(){
        NamespacedKey key = NamespacedKey.minecraft("purified_wand_core");
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, Purified_Wand_Core);
        shapedRecipe.shape("* *", "/+/", "* *");
        shapedRecipe.setIngredient('*', Material.DIAMOND_BLOCK);
        shapedRecipe.setIngredient('/', Material.DIAMOND);
        shapedRecipe.setIngredient('+', new RecipeChoice.ExactChoice(WandCore));
        Bukkit.getServer().addRecipe(shapedRecipe);
        recipes.add(key);
    }

    private void createNetheriteCore(){
        NamespacedKey key = NamespacedKey.minecraft("netherite_core");
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, Netherite_Core);
        shapedRecipe.shape("***", "*/*", "***");
        shapedRecipe.setIngredient('*', Material.NETHERITE_INGOT);
        shapedRecipe.setIngredient('/', Material.NETHER_STAR);
        Bukkit.getServer().addRecipe(shapedRecipe);
        recipes.add(key);
    }
    private void createStrawberryBowl(){
        NamespacedKey key = NamespacedKey.minecraft("strawberry_bowl");
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, Strawberry_Bowl);
        shapedRecipe.shape("   ", " //", " /*");
        shapedRecipe.setIngredient('/', new RecipeChoice.ExactChoice(Strawberry));
        shapedRecipe.setIngredient('*', Material.BOWL);
        recipes.add(key);
        Bukkit.getServer().addRecipe(shapedRecipe);
    }

    public void createRoastedStrawberry(){
        NamespacedKey key = NamespacedKey.minecraft("roasted_strawberry");
        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(key, Roasted_Strawberry, new RecipeChoice.ExactChoice(Strawberry), 0.35f, 20*4);
        Bukkit.getServer().addRecipe(furnaceRecipe);
        recipes.add(key);
    }

    public void createWandTier1(){
        NamespacedKey  key = NamespacedKey.minecraft("wand_tier_1");
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, Tier_1_Wand);
        shapedRecipe.shape("/  ", " * ", "  _");
        shapedRecipe.setIngredient('/', new RecipeChoice.ExactChoice(WandCore));
        shapedRecipe.setIngredient('*', Material.STICK);
        shapedRecipe.setIngredient('_', Material.BLACK_DYE);
        recipes.add(key);
        Bukkit.getServer().addRecipe(shapedRecipe);
    }

    public void createMagicalWood(){
        NamespacedKey key = NamespacedKey.minecraft("magical_wood");
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(key, Magical_Wood);
        shapelessRecipe.addIngredient(Material.OAK_LOG);
        shapelessRecipe.addIngredient(Material.BIRCH_LOG);
        shapelessRecipe.addIngredient(Material.SPRUCE_LOG);
        shapelessRecipe.addIngredient(Material.DARK_OAK_LOG);
        shapelessRecipe.addIngredient(Material.JUNGLE_LOG);
        shapelessRecipe.addIngredient(Material.ACACIA_LOG);
        shapelessRecipe.addIngredient(Material.MANGROVE_LOG);
        shapelessRecipe.addIngredient(Material.CRIMSON_STEM);
        shapelessRecipe.addIngredient(Material.WARPED_STEM);
        Bukkit.getServer().addRecipe(shapelessRecipe);
        recipes.add(key);
    }

    public void createSpellTable(){
        NamespacedKey key = NamespacedKey.minecraft("spell_table");
        ShapedRecipe recipe = new ShapedRecipe(key, SPELL_TABLE);
        recipe.shape("   ", "/*/", "+#+");
        recipe.setIngredient('/', Material.SCULK);
        recipe.setIngredient('*', Material.ENCHANTING_TABLE);
        recipe.setIngredient('+', Material.OBSIDIAN);
        recipe.setIngredient('#', Material.IRON_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
        recipes.add(key);
    }


}

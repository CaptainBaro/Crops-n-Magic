package de.darkyiu.crops_and_magic.custom_crafting;

import de.darkyiu.crops_and_magic.crops.Crop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.nio.file.WatchKey;

public class ItemManager {

    public static ItemStack Tier_2_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_2).build();
    public static ItemStack Magical_Wood = new CustomItemBuilder(CustomItems.MAGICAL_WOOD).build();
    public static ItemStack Strawberry = new CustomItemBuilder(Crop.STRAWBERRY).createEatingCrop(Crop.STRAWBERRY);
    public static ItemStack Roasted_Strawberry = new CustomItemBuilder(CustomFood.ROASTED_STRAWBERRY).createFood();
    public static ItemStack Strawberry_Bowl = new CustomItemBuilder(CustomFood.STRAWBERRY_BOWL).createFood();
    public static ItemStack WandCore = new CustomItemBuilder(CustomItems.WAND_CORE).build();
    public static ItemStack Tier_1_Wand = new CustomItemBuilder(CustomItems.WAND_TIER_1).build();
    public static ItemStack Netherite_Core = new CustomItemBuilder(CustomItems.NETHERITE_CORE).build();
    public static ItemStack[] tier_2_wand = new ItemStack[4];

    public void init(){
        initList();
        createWandCore();
        createNetheriteCore();
        createStrawberryBowl();
        createRoastedStrawberry();
        createWandTier1();
        createMagicalWood();
        createWandTier2();
    }

    private void initList(){
        tier_2_wand[0] = Magical_Wood;
        tier_2_wand[1] = Tier_1_Wand;
        tier_2_wand[2] = new ItemStack(Material.GOLD_INGOT, 10);
        tier_2_wand[3] = Tier_2_Wand;
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
    private void createStrawberryBowl(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("strawberry_bowl"), Strawberry_Bowl);
        shapedRecipe.shape("   ", " //", " /*");
        shapedRecipe.setIngredient('/', new RecipeChoice.ExactChoice(Strawberry));
        shapedRecipe.setIngredient('*', Material.BOWL);

        Bukkit.getServer().addRecipe(shapedRecipe);
    }

    public void createRoastedStrawberry(){
        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(NamespacedKey.minecraft("roasted_strawberry"), Roasted_Strawberry, new RecipeChoice.ExactChoice(Strawberry), 0.35f, 20*4);
        Bukkit.getServer().addRecipe(furnaceRecipe);
    }

    public void createWandTier1(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("wand_tier_1"), Tier_1_Wand);
        shapedRecipe.shape("/  ", " * ", "  _");
        shapedRecipe.setIngredient('/', new RecipeChoice.ExactChoice(WandCore));
        shapedRecipe.setIngredient('*', Material.STICK);
        shapedRecipe.setIngredient('_', Material.BLACK_DYE);

        Bukkit.getServer().addRecipe(shapedRecipe);
    }

    public void createMagicalWood(){
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(NamespacedKey.minecraft("magical_wood"), Magical_Wood);
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
    }

    public void createWandTier2(){
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("wand_tier_2"), Tier_2_Wand);
        shapedRecipe.shape("/  ", " * ", "  _");
        shapedRecipe.setIngredient('/', new RecipeChoice.ExactChoice(Magical_Wood));
        shapedRecipe.setIngredient('*', new RecipeChoice.ExactChoice(Tier_1_Wand));
        shapedRecipe.setIngredient('_', Material.GREEN_DYE);

        Bukkit.getServer().addRecipe(shapedRecipe);
    }

}

package de.darkyiu.crops_and_magic.custom_crafting;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum CustomFood {

    ROASTED_STRAWBERRY("§fRoasted Strawberry", null, Material.SWEET_BERRIES, "Item.Food.Roasted_Strawberry", 791, 2, 1f, null, 0),
    STRAWBERRY_BOWL("§fStrawberries in a bowl", null, Material.BEETROOT_SOUP, "Item.Food.Strawberry_Bowl", 790, 4, 4f,null, 0);

    private String name;
    private String lore;
    private Material material;
    private String localizedName;
    private int customModelData;
    private int food;
    private float saturation;
    private PotionEffect effect;
    private double chance;


    CustomFood(String name, String lore, Material material, String localizedName, int customModelData, int food, float saturation, PotionEffect effect, double chance){
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.localizedName = localizedName;
        this.customModelData = customModelData;
        this.food = food;
        this.saturation = saturation;
        this.effect = effect;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public int getFood() {
        return food;
    }

    public float getSaturation() {
        return saturation;
    }

    public PotionEffect getEffect() {
        return effect;
    }

    public double getChance() {
        return chance;
    }
}

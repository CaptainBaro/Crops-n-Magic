package de.darkyiu.crops_and_magic.custom_crafting;

import org.bukkit.Material;

public enum CustomItems {

    WAND_CORE("§5Wand Core", "§7A important piece for crafting a wand.", Material.DIAMOND, "Item.Wand_Core", 3454, true, true);

    private String name;
    private String lore;
    private Material material;
    private String localizedName;
    private int customModelData;
    private boolean enchanted;
    private boolean unbreakable;

    CustomItems(String name, String lore, Material material, String localizedName, int customModelData, boolean enchanted, boolean unbreakable){
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.localizedName = localizedName;
        this.customModelData = customModelData;
        this.enchanted = enchanted;
        this.unbreakable = unbreakable;
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

    public boolean isEnchanted() {
        return enchanted;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }
}

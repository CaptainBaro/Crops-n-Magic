package de.darkyiu.crops_and_magic.custom_crafting;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum WandUpgradeModule {

    NORMAL_COOLDOWN(ChatColor.GOLD + "Normal Cooldown", "Item.Upgrade.Normal_Cooldown", Material.GOLD_INGOT, 4, 10, 0,0, "This is the basic version of the cooldown reducing wand upgrade module.");

    private String name;
    private String id;
    private Material material;
    private int tier;
    private double cooldown_reduction;
    private double mana_reduction;
    private double damage_increasing;
    private String lore;


    WandUpgradeModule(String name, String id, Material material, int tier, double cooldown_reduction, double mana_reduction,double damage_increasing, String lore){
        this.name = name;
        this.id = id;
        this.material = material;
        this.tier = tier;
        this.cooldown_reduction = cooldown_reduction;
        this.mana_reduction = mana_reduction;
        this.damage_increasing = damage_increasing;
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public int getTier() {
        return tier;
    }

    public double getCooldown_reduction() {
        return cooldown_reduction;
    }

    public double getDamage_increasing() {
        return damage_increasing;
    }

    public String getLore() {
        return lore;
    }

    public double getMana_reduction() {
        return mana_reduction;
    }
}

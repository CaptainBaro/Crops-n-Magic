package de.darkyiu.crops_and_magic.custom_crafting;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum CustomItems {

    SPELL_ASSEMBLE_BLOCK("§fSpell Assemble Table", null, Material.ENCHANTING_TABLE, "Block.Spell_Assemble_Block", 6789, false, true),
    SPELL_ASSEMBLE_BLOCK_ITEM("§fSpell Assemble Table", null, Material.ENCHANTING_TABLE, "Block_Item.Spell_Assemble_Block", 9576, true, true),
    WAND_TIER_2(ChatColor.AQUA + "Wand[Tier 2]", "§7Spells:", Material.STICK, "Item.Wand.2", 6790, false, true),
    WAND_TIER_1("§2Wand[Tier 1]", "§7Spells:", Material.STICK, "Item.Wand.1", 6789, false, true),
    WAND_CORE("§2Wand Core", "§7A important piece for crafting a wand.", Material.DIAMOND, "Item.Wand_Core", 3454, true, true),
    MAGICAL_WOOD(ChatColor.AQUA + "Magical Wood", "§7A piece of different §7types of wood. Used for §7crafting wands.", Material.MANGROVE_LOG, "Block_Item.Magical_Wood", 2341, true, true),
    NETHERITE_CORE("§dNetherite Core", "§7One of the most rarest §7items. Used for crafting a Tier 5 Wand.", Material.NETHER_STAR, "Item.Netherite_Core", 4567, true, true);

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

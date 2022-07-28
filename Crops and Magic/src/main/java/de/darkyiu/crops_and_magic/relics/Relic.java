package de.darkyiu.crops_and_magic.relics;

import de.darkyiu.crops_and_magic.relics.relic_abilities.HealingRelic;
import net.md_5.bungee.api.ChatColor;

public enum Relic {

    HEALING_RELIC(ChatColor.LIGHT_PURPLE + "Sacred Runestone of the Shaman", "§7This sacred runestone was owned by one the mighty shamans, that live in the farlands. It said, it can heal it's owner incredible good.", "Item.Relic.Healing_Relic",
            46767, true, true, new HealingRelic(), 30);

    private String name;
    private String lore;
    private String localizedName;
    private int customModelData;
    private boolean enchanted;
    private boolean unbreakable;
    private RelicAbility relicAbility;
    private int cooldDown;

    Relic(String name, String lore, String localizedName, int customModelData, boolean enchanted, boolean unbreakable, RelicAbility relicAbility, int cooldDown){
        this.name = name;
        this.lore = lore;
        this.localizedName = localizedName;
        this.customModelData = customModelData;
        this.enchanted = enchanted;
        this.unbreakable = unbreakable;
        this.relicAbility = relicAbility;
        this.cooldDown = cooldDown;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
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

    public RelicAbility getRelicAbility() {
        return relicAbility;
    }

    public int getCooldDown() {
        return cooldDown;
    }
}

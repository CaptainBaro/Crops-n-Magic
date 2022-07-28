package de.darkyiu.crops_and_magic.spells;

import net.md_5.bungee.api.ChatColor;

public enum Spell {

    //Tier 1: Dark Green Tier 2: Aqua Tier 3: Yellow Tier 4: Gold Tier 5: Pink/Purple
    GREAT_HEAL("Heal of the Elves", "Spell.4.Great_Heal", 4, ChatColor.GOLD, "§7A heal used by the legendary elves. Heals a great amount.", 3451, new GreatHeal()),
    BIGGER_HEAL("Heal of the Shaman", "Spell.3.Bigger_Heal", 3, ChatColor.DARK_AQUA, "§7The shamans of the farlands used this spell to heal her allies.", 3467, new BiggerHeal()),
    DECENT_HEAL("Heal of the Wandering Mage", "Spell.2.Decent_Heal", 2, ChatColor.AQUA, "§7A decemt but not great heal. Once used by a wandering mage.", 3745, new DecentHeal()),
    SMALL_HEAL("Heal of the Charlatan", "Spell.1.Small_Heal", 1, ChatColor.DARK_GREEN, "§7A small heal that got used by a charlatan.", 8734, new SmallHeal());

    private final String name;
    private final String localizedName;
    private final int tier;
    private final ChatColor color;
    private final String lore;
    private final int customModelData;
    private SpellAbility spellAbility;

    Spell(String name, String localizedName,int tier, ChatColor color, String lore, int customModelData, SpellAbility spellAbility){
        this.name = name;
        this.localizedName = localizedName;
        this.tier = tier;
        this.color = color;
        this.lore = lore;
        this.customModelData = customModelData;
        this.spellAbility = spellAbility;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getLore() {
        return lore;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public SpellAbility getSpellAbility() {
        return spellAbility;
    }

    public String getLocalizedName() {
        return localizedName;
    }


}

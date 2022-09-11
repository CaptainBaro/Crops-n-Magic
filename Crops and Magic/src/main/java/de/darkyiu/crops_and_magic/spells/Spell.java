package de.darkyiu.crops_and_magic.spells;

import de.darkyiu.crops_and_magic.spells.spell_abilities.*;
import net.md_5.bungee.api.ChatColor;

public enum Spell {

    BUFF_HARM("Senpukko", "Spell_3_Buff_Harm", 3 , ChatColor.YELLOW, "§7Lets you hurt yourself like a samurai to get a buff in magic.", 2000, new SamuraiBuff(), 5, 5),
    GLOWING_LASER("Spectral Laser", "Spell_2_Glowing_Laser", 2, ChatColor.AQUA,"§7A laser which will light up the enemies.", 2000, new GlowingSpell(), 1, 5),
    MOUNT_SPELL("Mount_Spell", "Spell_2_Mount_Spell", 2, ChatColor.AQUA, "§7Summon a horse to ride on. Mount will be sent into the fourth dimension after sneaking.", 2000, new MountSpell(), 3, 10),
    DAY_SPELL("Wish for day", "Spell_4_Day_Spell", 4, ChatColor.GOLD, "§7A spell which uses the wish for day to fasten the time till it's daytime.", 2000, new DaySpell(), 5, 40),
    FREEZE_LASER("Iceologer's Spell", "Spell_2_Freeze_Laser", 2, ChatColor.AQUA, "§7A laser which freezes on impact.", 2000, new FreezeLaser(), 1, 5),
    BETTER_EXPLOSION_LASER("Geomancer's Mastery", "Spell_5_Explosion_Laser_Mastery", 5, ChatColor.LIGHT_PURPLE, "The mastery of the geomancer. Explodes on impact and gives damaged mobs the effect of the hitted one.", 2000, new BetterExplosionLaser(), 1, 10),
    EXPLOSION_Laser("Geomancer's Spell", "Spell_4_Explosion_Laser", 4, ChatColor.GOLD, "§7A laser spell which explodes on impact.", 2000, new ExplosionLaser(), 3, 5),
    GREAT_CLEANING("Spiritual Cleaning","Spell_5_Great_Cleaning", 5, ChatColor.LIGHT_PURPLE, "§7A great spiriual cleaning which removes every effect.", 2000, new GreatCleaning(), 3, 30),
    MEDIUM_CLEANING("Cleaning of the Shaman", "Spell_3_Medium_Cleaning",3, ChatColor.YELLOW, "§7The shamans used this to clean themselves from negetive effects from their enviroment. Remove two random potion effects.", 2000, new MediumCleaning(), 3, 30),
    BASIC_CLEANING("Magic Cleaning", "Spell_2_Basic_Cleaning",2, ChatColor.AQUA, "§7Cleans you on a magic way. Removes one random potion effect.", 2000, new BasicCleaning(),3 ,30),
    GREAT_LASER("Blazing Laser", "Spell_5_Great_Laser", 5, ChatColor.LIGHT_PURPLE, "§7A powerful laser which takes it power from the nether dimeonsion. Stronger in the nether and inflicts fire on impact.", 2000, new BlazingLaser(), 1, 10),
    MEDIUM_LASER("Water Laser", "Spell_3_Medium_Laser", 3, ChatColor.YELLOW, "§7A laser which uses the power of the ocean. Really strong when the mage is in the water.", 2000, new WaterLaser(), 1,7),
    BASIC_LASER("Nature Laser", "Spell_1_Basic_Laser",1, ChatColor.DARK_GREEN, "§7A small laser spell which lets you shot a laser. Uses the power of the nature", 2000, new LaserSpell(), 1,5),
    GREAT_TELEPORT("Enderian Teleport", "Spell_4_Great_Teleport", 4, ChatColor.GOLD, "§7The teleport of the enderians that conquer the lands far in the end dimension.", 2000, new EnderianTeleport(), 0, 20),
    SMALL_TELEPORT("Magic Teleport", "Spell_2_Small_Teleport",2,ChatColor.AQUA, "§7A small teleportation scroll. Let's you teleport 8 blocks.", 2000, new TeleportScoll(), 0,20),
    LIGHNTING("Lightning of the skald", "Spell_3_Lightning", 3, ChatColor.YELLOW, "§7The skalds in the far north use the power of their mighty god Klaos to strike powerful lightnings.", 2000, new LightningAbility(), 1, 10),
    GREAT_HEAL("Heal of the Elves", "Spell_4_Great_Heal", 4, ChatColor.GOLD, "§7A heal used by the legendary elves. Heals a great amount.", 2000, new GreatHeal(), 10, 10),
    BIGGER_HEAL("Heal of the Shaman", "Spell_3_Bigger_Heal", 3, ChatColor.YELLOW, "§7The shamans of the farlands used this spell to heal her allies.", 2000, new BiggerHeal(), 10, 10),
    DECENT_HEAL("Heal of the Wandering Mage", "Spell_2_Decent_Heal", 2, ChatColor.AQUA, "§7A decemt but not great heal. Once used by a wandering mage.", 2000, new DecentHeal(), 10, 10),
    SMALL_HEAL("Heal of the Charlatan", "Spell_1_Small_Heal", 1, ChatColor.DARK_GREEN, "§7A small heal that got used by a charlatan.", 2000, new SmallHeal(), 10, 10);

    private final String name;
    private final String localizedName;
    private final int tier;
    private final ChatColor color;
    private final String lore;
    private final int customModelData;
    private final SpellAbility spellAbility;
    private int coolDown;
    private double mana;

    Spell(String name, String localizedName,int tier, ChatColor color, String lore, int customModelData, SpellAbility spellAbility, int coolDown, double mana){
        this.name = name;
        this.localizedName = localizedName;
        this.tier = tier;
        this.color = color;
        this.lore = lore;
        this.customModelData = customModelData;
        this.spellAbility = spellAbility;
        this.coolDown = coolDown;
        this.mana = mana;
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

    public int getCoolDown() {
        return coolDown;
    }

    public double getMana() {
        return mana;
    }
}

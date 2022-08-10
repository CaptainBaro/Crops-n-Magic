package de.darkyiu.crops_and_magic.relics;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import de.darkyiu.crops_and_magic.relics.relic_abilities.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public enum Relic {

    DISCOVERY_RELIC(ChatColor.LIGHT_PURPLE + "Discovery Relic", "§7Placeholder. Lets you see what others do.", "Item.Relic.Discovery_Relic", 4684, false, true, new DiscoveryRelic(), 3),
    LEAP_RELIC(ChatColor.LIGHT_PURPLE + "Spider's Fang", "§7Lets you make a big leap.", "Item.Relic.Leap_Relic", 4683, false, true, new LeapAbility(), 3),
    UNDERWATER_BREATHING_RELIC(ChatColor.LIGHT_PURPLE + "Mermaid's Tear", "§7A tear of a rare mermaid. If you have, you don't have to worry about drowning because the mermaids magic protects you.", "Item.Relic.Breathing_Relic", 4682,
            false,true, new BreathingAbility(),0),
    LAST_CHANCE_RELIC(ChatColor.LIGHT_PURPLE + "Last Chance Relic", "§7GIves you strength when you are under 2 hearts.", "Item.Relic.Last_Chance_Relic", 4681, false,
            true, new LastChanceRelic(), 10),
    ORE_HUNTER_RELIC(ChatColor.LIGHT_PURPLE + "Archaeologist's Shovel", "§7A shovel of archaeologist which lets you see the ores in your enviroment.", "Item.Relic.Ore_Hunter_Relic", 4680, false,
            true, new OreHunterRelic(), 10),
    GLOWING_RELIC(ChatColor.LIGHT_PURPLE + "Phantom's Eye", "§7Lets you see the entities in the world. Applies glowing to every entitie.", "Item.Relic.Glowing_Relic", 4679, false,
            true, new GlowingRelic(), 10),
    TRANSPORTATION_RELIC(ChatColor.LIGHT_PURPLE + "Enderian Pocket", "§7Lets you open your enderchest.", "Iem.Relic.Transportation_Relic", 4678, false,
            true, new TransportationRelic(), 10),
    GROWTH_RELIC(ChatColor.LIGHT_PURPLE + "Magical Wheat", "§7A wheat which was touched by the leader of the shamans. After that it was used for growing crops. Eventually it got lost.", "Item.Relic.Growth_Relic", 4677, true,
            true,new GrowthRelic(), 10),
    FEAR_RELIC(ChatColor.LIGHT_PURPLE + "Warden's Heart", "§7The heart of a warden. Use it to fear your enemies and confuse them.", "Item.Relic.Fear_Relic", 4676, false, true, new FearRelic(), 30),
    HEALING_RELIC(ChatColor.LIGHT_PURPLE + "Sacred Runestone of the Shaman", "§7This sacred runestone was owned by one the mighty shamans, that live in the farlands. It said, it can heal it's owner incredible good.", "Item.Relic.Healing_Relic",
            4675, true, true, new HealingRelic(), 30);

    private final String name;
    private final String lore;
    private final String localizedName;
    private final int customModelData;
    private final boolean enchanted;
    private final boolean unbreakable;
    private final RelicAbility relicAbility;
    private final int cooldDown;

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

    public static void relicFound(){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle("§cLost Relic", "§fA lost relic was found.", 10, 80, 20);
            player.playSound(player, Sound.ENTITY_WITHER_DEATH, 5, 2);
        }
    }
}

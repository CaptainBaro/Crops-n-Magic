package de.darkyiu.crops_and_magic.wand;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.spells.Spell;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpellListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem()==null)return;
        if (event.getItem().getItemMeta()==null)return;
        if (event.getItem().getItemMeta().getLocalizedName().contains("Item.Wand.")){
            int spell = Main.getPlugin().getWandSpellHashmap().getSpell(event.getItem().getItemMeta().getLocalizedName());
            switch (spell){
                case 1:
                    if (CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1"))!=null){
                        Spell spell1 = CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1"));
                        spell1.getSpellAbility().onRightCLick(event.getPlayer(), event.getItem());
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1").equalsIgnoreCase("Open")) {
                        event.getPlayer().sendMessage("§cYou dont have a spell on this slot.");
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1").equalsIgnoreCase("Locked")) {
                        event.getPlayer().sendMessage("§cYou don't have unlocked this slot.");
                    }
                    break;
                case 2:
                    if (CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2"))!=null){
                    Spell spell1 = CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2"));
                    spell1.getSpellAbility().onRightCLick(event.getPlayer(), event.getItem());
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2").equalsIgnoreCase("Open")) {
                    event.getPlayer().sendMessage("§cYou dont have a spell on this slot.");
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2").equalsIgnoreCase("Locked")) {
                    event.getPlayer().sendMessage("§cYou don't have unlocked this slot.");
                    }
                    break;
                case 3:
                    if (CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3"))!=null){
                        Spell spell1 = CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3"));
                        spell1.getSpellAbility().onRightCLick(event.getPlayer(), event.getItem());
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3").equalsIgnoreCase("Open")) {
                        event.getPlayer().sendMessage("§cYou dont have a spell on this slot.");
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3").equalsIgnoreCase("Locked")) {
                        event.getPlayer().sendMessage("§cYou don't have unlocked this slot.");
                    }
                    break;
            }
        }
    }
}

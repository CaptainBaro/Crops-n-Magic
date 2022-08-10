package de.darkyiu.crops_and_magic.wand;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.WandUpgradeModule;
import de.darkyiu.crops_and_magic.spells.Spell;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.TimeUnit;

public class SpellListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem()==null)return;
        if (event.getItem().getItemMeta()==null)return;
        if (event.getItem().getItemMeta().getLocalizedName().contains("Item.Wand.")){
            int spell = Main.getPlugin().getWandSpellHashmap().getSpell(event.getItem().getItemMeta().getLocalizedName());
            String wandPath = event.getItem().getItemMeta().getLocalizedName();
            switch (spell){
                case 1:
                    if (CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1"))!=null){
                        Spell spell1 = CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1"));
                        if (onCooldown(1, event.getPlayer(), spell1, wandPath)){
                            spell1.getSpellAbility().onRightCLick(event.getPlayer(), event.getItem());
                        }
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1").equalsIgnoreCase("Open")) {
                        event.getPlayer().sendMessage("§cYou dont have a spell on this slot.");
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_1").equalsIgnoreCase("Locked")) {
                        event.getPlayer().sendMessage("§cYou don't have unlocked this slot.");
                    }
                    break;
                case 2:
                    if (CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2"))!=null){
                    Spell spell1 = CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2"));
                        if (onCooldown(2, event.getPlayer(), spell1, wandPath)){
                        spell1.getSpellAbility().onRightCLick(event.getPlayer(), event.getItem());
                        }
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2").equalsIgnoreCase("Open")) {
                    event.getPlayer().sendMessage("§cYou dont have a spell on this slot.");
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_2").equalsIgnoreCase("Locked")) {
                    event.getPlayer().sendMessage("§cYou don't have unlocked this slot.");
                    }
                    break;
                case 3:
                    if (CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3"))!=null){
                        Spell spell1 = CustomItemBuilder.getSpell(Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3"));
                        if (onCooldown(3, event.getPlayer(), spell1, wandPath)){
                            spell1.getSpellAbility().onRightCLick(event.getPlayer(), event.getItem());
                        }
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3").equalsIgnoreCase("Open")) {
                        event.getPlayer().sendMessage("§cYou dont have a spell on this slot.");
                    }else if (Main.getPlugin().getWandFile().getConfiguration().getString(event.getItem().getItemMeta().getLocalizedName() + ".Spell_3").equalsIgnoreCase("Locked")) {
                        event.getPlayer().sendMessage("§cYou don't have unlocked this slot.");
                    }
                    break;
            }
        }
    }
    public static boolean onCooldown(int spellslot, Player player, Spell spell, String wandPath){
        String playerItem = player.getUniqueId().toString() + spellslot;
        long cooldown = spell.getCoolDown()*1000L;
        FileConfiguration config = Main.getPlugin().getWandFile().getConfiguration();
        if (CustomItemBuilder.getWandUpgrade(config.getString(wandPath + ".Upgrade_1"))!=null){
            WandUpgradeModule wandUpgradeModule = CustomItemBuilder.getWandUpgrade(config.getString(wandPath + ".Upgrade_1"));
            assert wandUpgradeModule != null;
            if (wandUpgradeModule.getCooldown_reduction()!=0){
                cooldown = (long) (cooldown - cooldown*(wandUpgradeModule.getCooldown_reduction()/100));
            }
        }
        if (CustomItemBuilder.getWandUpgrade(config.getString(wandPath + ".Upgrade_2"))!=null){
            WandUpgradeModule wandUpgradeModule = CustomItemBuilder.getWandUpgrade(config.getString(wandPath + ".Upgrade_2"));
            assert wandUpgradeModule != null;
            if (wandUpgradeModule.getCooldown_reduction()!=0){
                cooldown = (long) (cooldown - cooldown*(wandUpgradeModule.getCooldown_reduction()/100));
            }
        }
        long timeleft = System.currentTimeMillis() - Main.getPlugin().getCooldownManager().getCooldown(playerItem);
        if (TimeUnit.MILLISECONDS.toMillis(timeleft) >= cooldown){
            Main.getPlugin().getCooldownManager().setCooldown(playerItem, System.currentTimeMillis());
            return true;
        }else {
            player.sendMessage("§cThis spell is still charging.");
            return false;
        }
    }

}

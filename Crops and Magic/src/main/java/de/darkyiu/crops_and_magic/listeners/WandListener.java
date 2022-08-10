package de.darkyiu.crops_and_magic.listeners;

import com.sun.jna.platform.win32.WinNT;
import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.concurrent.TimeUnit;

public class WandListener implements Listener {


    @EventHandler(priority =  EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem()==null)return;
            if (event.getCurrentItem().getItemMeta()==null)return;
            if (event.getCurrentItem().getItemMeta().getLocalizedName()==null)return;
            if (event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Item.Wand.1") || event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Item.Wand.2") || event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Item.Wand.3") || event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Item.Wand.4") || event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Item.Wand.5")){
                String locName = event.getCurrentItem().getItemMeta().getLocalizedName();

                ItemMeta meta = event.getCurrentItem().getItemMeta();
                double r = Math.random()*1000000;
                int ri = (int) r;
                Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Spell_1", "Open");
                if (CustomItemBuilder.getTier(locName)>1){
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Spell_2", "Open");
                }else {
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Spell_2", "Locked");
                }
                if (CustomItemBuilder.getTier(locName)>2){
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Spell_3", "Open");
                }else {
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Spell_3", "Locked");
                }
                if (CustomItemBuilder.getTier(locName)>3){
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Upgrade_1", "Open");
                }else{
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Upgrade_1", "Locked");
                }
                if (CustomItemBuilder.getTier(locName)>4){
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Upgrade_2", "Open");
                }else{
                    Main.getPlugin().getWandFile().set(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri + ".Upgrade_2", "Locked");
                }
                meta.setLocalizedName(meta.getLocalizedName() + "." + player.getUniqueId() + "." + ri);
                event.getCurrentItem().setItemMeta(meta);
                Main.getPlugin().getWandFile().save();
            }
        }
    }



}

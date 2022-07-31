package de.darkyiu.crops_and_magic.wand;

import com.sun.jna.platform.win32.WinNT;
import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.spells.Spell;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import de.darkyiu.crops_and_magic.util.Config;
import de.darkyiu.crops_and_magic.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

public class WandAssemblyTable implements Listener {

    public static final String title_first = "§8Spell Assemble Table";
    public static String title_second = "§8Spell Assemble Table: Change spell";
    public static String upgrade_Title = "§8Upgrade your wands";


    public static Inventory createGui(String title){
        Inventory inventory = Bukkit.createInventory(null, 54, title);
        for (int i=0; i<54; i++){
            inventory.setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("  ").build());
        }
        inventory.setItem(13, new ItemStack(Material.AIR));
        inventory.setItem(38, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fPlease input a wand.").build());
        inventory.setItem(40, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fPlease input a wand.").build());
        inventory.setItem(18, new ItemBuilder(Material.STICK).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§aUpgrade your wands").setLocalizedName("Gui.Upgrade").build());
        inventory.setItem(42, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fPlease input a wand.").build());
        inventory.setItem(49, new ItemBuilder(Material.BARRIER).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§cClose").setLocalizedName("Gui.Close").build());
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§8Spell Assemble Table"))return;
        if (event.getCurrentItem()==null)return;
        if (event.getCurrentItem().getItemMeta()==null) {
            event.setCancelled(true);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Close")){
            event.getWhoClicked().closeInventory();
        }else if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Upgrade")){
            Inventory inventory = Bukkit.createInventory(null, 54, upgrade_Title);
            for (int i=0; i<54; i++){
                inventory.setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("  ").setLocalizedName("Gui.Element").build());
            }
            inventory.setItem(10, new ItemBuilder(Material.MANGROVE_LOG).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.DURABILITY, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setName("§fPut your upgrade core or magical item in here.").setLocalizedName("Gui.Element").build());
            inventory.setItem(13, new ItemBuilder(Material.STICK).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.DURABILITY, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setName("§fPut your wand in here").setLocalizedName("Gui.Element").build());
            inventory.setItem(16, new ItemBuilder(Material.GOLD_INGOT).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fPut your other upgrade material in here.").setLocalizedName("Gui.Element").build());
            inventory.setItem(22, new ItemStack(Material.AIR));
            inventory.setItem(19, new ItemStack(Material.AIR));
            inventory.setItem(25, new ItemStack(Material.AIR));
            inventory.setItem(40, new ItemStack(Material.AIR));
            inventory.setItem(43, new ItemBuilder(Material.GREEN_DYE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§aCraft").setLocalizedName("Gui.Craft").build());
            inventory.setItem(37, new ItemBuilder(Material.RED_DYE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§cAbort crafting").setLocalizedName("Gui.Abort").build());
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().openInventory(inventory);
        }
        if (CustomItemBuilder.getCustomItem(event.getCurrentItem().getItemMeta().getLocalizedName())!=null) {
            CustomItems customItems = CustomItemBuilder.getCustomItem(event.getCurrentItem().getItemMeta().getLocalizedName());
            if (customItems.getLocalizedName().contains("Item.Wand.")) {

                    ItemStack[] contents = Objects.requireNonNull(event.getClickedInventory()).getContents();

                    Inventory inventory = createGui(title_second);
                    inventory.setItem(13, event.getCurrentItem());
                    inventory.setItem(49, new ItemBuilder(Material.GREEN_DYE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fFinish").setLocalizedName("Gui.Close").build());
                    Config config = Main.getPlugin().getWandFile();
                    if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_1").equalsIgnoreCase("open")){
                        inventory.setItem(38, new ItemStack(Material.AIR));
                    }else if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_1").contains("Spell")){
                        Spell spell = CustomItemBuilder.getSpell(config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_1"));
                        inventory.setItem(38, new CustomItemBuilder(spell).createSpell());
                    }
                    if (CustomItemBuilder.getTier(event.getCurrentItem().getItemMeta().getLocalizedName())>1) {
                        if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_2").equalsIgnoreCase("open")) {
                            inventory.setItem(40, new ItemStack(Material.AIR));
                        } else if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_2").contains("Spell")) {
                            Spell spell = CustomItemBuilder.getSpell(config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_2"));
                            inventory.setItem(40, new CustomItemBuilder(spell).createSpell());
                        }
                    } else{
                        inventory.setItem(40, new ItemBuilder(Material.BARRIER).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fNot unlocked yet").build());
                    }
                    if (CustomItemBuilder.getTier(event.getCurrentItem().getItemMeta().getLocalizedName())>2) {
                        if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_3").equalsIgnoreCase("open")) {
                        inventory.setItem(42, new ItemStack(Material.AIR));
                        } else if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_3").contains("Spell")) {
                        Spell spell = CustomItemBuilder.getSpell(config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Spell_3"));
                        inventory.setItem(42, new CustomItemBuilder(spell).createSpell());
                        }
                    } else{
                        inventory.setItem(42, new ItemBuilder(Material.BARRIER).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fNot unlocked yet").build());
                    }


                    event.getClickedInventory().remove(event.getCurrentItem());
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().openInventory(inventory);
            } else {
                event.setCancelled(true);
            }
        }else if (CustomItemBuilder.getSpell(event.getCurrentItem().getItemMeta().getLocalizedName())!=null){
            Spell spell = CustomItemBuilder.getSpell(event.getCurrentItem().getItemMeta().getLocalizedName());

        }else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals(upgrade_Title)){
            if (event.getCurrentItem()==null)return;
            if (event.getCurrentItem().getItemMeta()==null)return;
            if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Element")){
                event.setCancelled(true);
                return;
            }
            if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Abort")){
                if (event.getClickedInventory().getItem(19)!=null){
                    BasicUtility.giveItemSavely(event.getWhoClicked(), Objects.requireNonNull(event.getClickedInventory()).getItem(19));
                }
                if (event.getClickedInventory().getItem(22)!=null){
                    BasicUtility.giveItemSavely(event.getWhoClicked(), Objects.requireNonNull(event.getClickedInventory()).getItem(22));
                }
                if (event.getClickedInventory().getItem(25)!=null){
                    BasicUtility.giveItemSavely(event.getWhoClicked(), Objects.requireNonNull(event.getClickedInventory()).getItem(25));
                }
                event.getWhoClicked().closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Craft")){
                event.setCancelled(true);
                ItemStack[] itemStacks = new ItemStack[3];
                if (event.getClickedInventory().getItem(19)!=null){
                    itemStacks[0] = event.getClickedInventory().getItem(19);
                }else {
                    itemStacks[0] = new ItemStack(Material.AIR);
                }
                if (event.getClickedInventory().getItem(22)!=null){
                    itemStacks[1] = event.getClickedInventory().getItem(22);
                }else {
                    itemStacks[1] = new ItemStack(Material.AIR);
                }
                if (event.getClickedInventory().getItem(25)!=null){
                    itemStacks[2] = event.getClickedInventory().getItem(25);
                }else {
                    itemStacks[2] = new ItemStack(Material.AIR);
                }
                if (itemStacks[0].getItemMeta().getLocalizedName().contains(ItemManager.tier_2_wand[0].getItemMeta().getLocalizedName())){
                    if (itemStacks[1].getItemMeta().getLocalizedName().contains(ItemManager.tier_2_wand[1].getItemMeta().getLocalizedName())){
                        if (itemStacks[2].getItemMeta().getLocalizedName().contains(ItemManager.tier_2_wand[2].getItemMeta().getLocalizedName()) || itemStacks[2].equals(ItemManager.tier_2_wand[2])){
                            BasicUtility.giveItemSavely(event.getWhoClicked(), ItemManager.tier_2_wand[3]);
                            event.getWhoClicked().closeInventory();
                        }
                    }
                }

            }
        }
        if (!event.getView().getTitle().equals(title_second))return;
        if (event.getCurrentItem()==null)return;
        if (event.getCurrentItem().getItemMeta()==null){
            event.setCancelled(true);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Close")){
            event.getWhoClicked().closeInventory();
        }
        if (CustomItemBuilder.getSpell(event.getCurrentItem().getItemMeta().getLocalizedName())!=null){

        }else {
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getView().getTitle().equals(title_second)){
            Inventory inventory = event.getInventory();

            ArrayList<String> lore = new ArrayList<>();
            if (inventory.getItem(38)!=null){
                if (inventory.getItem(38).getType().equals(Material.AIR)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Open");
                    lore.add("§7[§8Open§7]");
                } else if (inventory.getItem(38).getType().equals(Material.BARRIER)) {
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Locked");
                    lore.add("§7[§cLocked§7]");
                }else {
                    Spell spell = CustomItemBuilder.getSpell(inventory.getItem(38).getItemMeta().getLocalizedName());
                    if (spell.getTier()>CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())){
                        event.getPlayer().getInventory().addItem(inventory.getItem(38));
                        event.getPlayer().sendMessage("§cThe spell had a higher tier than your wand.");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Open");
                    }else {
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", inventory.getItem(38).getItemMeta().getLocalizedName());
                        lore.add("§7[" + spell.getColor() + spell.getName() + "§7]");
                    }
                }
            }else {
                 Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Open");
                 lore.add("§7[§8Open§7]");
            }
            if (inventory.getItem(40)!=null){
                if (inventory.getItem(40).getType().equals(Material.AIR)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", "Open");
                    lore.add("§7[§8Open§7]");
                } else if (inventory.getItem(40).getType().equals(Material.BARRIER)) {
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", "Locked");
                    lore.add("§7[§cLocked§7]");
                }else {
                    Spell spell = CustomItemBuilder.getSpell(inventory.getItem(40).getItemMeta().getLocalizedName());
                    if (spell.getTier()>CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())){
                        event.getPlayer().getInventory().addItem(inventory.getItem(40));
                        event.getPlayer().sendMessage("§cThe spell had a higher tier than your wand.");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", "Open");
                    }else {
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", inventory.getItem(40).getItemMeta().getLocalizedName());
                        lore.add("§7[" + spell.getColor() + spell.getName() + "§7]");
                    }
                }
            }else {
                Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", "Open");
                lore.add("§7[§8Open§7]");
            }

            if (inventory.getItem(42)!=null){
                if (inventory.getItem(42).getType().equals(Material.AIR)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Open");
                    lore.add("§7[§8Open§7]");
                } else if (inventory.getItem(42).getType().equals(Material.BARRIER)) {
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Locked");
                    lore.add("§7[§cLocked§7]");
                }else {
                    Spell spell = CustomItemBuilder.getSpell(inventory.getItem(42).getItemMeta().getLocalizedName());
                    if (spell.getTier()>CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())){
                        event.getPlayer().getInventory().addItem(inventory.getItem(42));
                        event.getPlayer().sendMessage("§cThe spell had a higher tier than your wand.");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Open");
                    }else {
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", inventory.getItem(42).getItemMeta().getLocalizedName());
                        lore.add("§7[" + spell.getColor() + spell.getName() + "§7]");
                    }
                }
            }else {
                Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Open");
                lore.add("§7[§8Open§7]");
            }
            ItemStack itemStack = event.getInventory().getItem(13);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            BasicUtility.giveItemSavely(event.getPlayer(),itemStack);
            Main.getPlugin().getWandFile().save();

        }else if (event.getView().getTitle().equals(title_first)){
            if (event.getInventory().getItem(13)==null){
                return;
            }
            BasicUtility.giveItemSavely(event.getPlayer(), event.getInventory().getItem(13));
        }
    }



}

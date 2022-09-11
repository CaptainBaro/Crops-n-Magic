package de.darkyiu.crops_and_magic.wand;

import com.sun.jna.platform.win32.WinNT;
import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.custom_crafting.WandUpgradeModule;
import de.darkyiu.crops_and_magic.spells.Spell;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import de.darkyiu.crops_and_magic.util.Config;
import de.darkyiu.crops_and_magic.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.print.DocFlavor;
import java.util.*;

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
        inventory.setItem(27, new ItemBuilder(Material.CRAFTING_TABLE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§aCraft custom items").setLocalizedName("Gui.Craft").build());
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
        }else if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Craft")){
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().openInventory(new CraftingTable().createCraftingTable());
            return;
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
                    if (CustomItemBuilder.getTier(event.getCurrentItem().getItemMeta().getLocalizedName())>=4){
                        if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Upgrade_1").equalsIgnoreCase("open")){
                            inventory.setItem(39, new ItemStack(Material.AIR));
                        }else if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Upgrade_1").contains("Upgrade")){
                            WandUpgradeModule upgradeModule = CustomItemBuilder.getWandUpgrade(config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Upgrade_1"));
                            inventory.setItem(39, new CustomItemBuilder(upgradeModule).createUpgradeModule());
                        }
                        if (CustomItemBuilder.getTier(event.getCurrentItem().getItemMeta().getLocalizedName())>=5){
                            if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Upgrade_2").equalsIgnoreCase("open")){
                                inventory.setItem(41, new ItemStack(Material.AIR));
                            }else if (config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Upgrade_2").contains("Upgrade")){
                                WandUpgradeModule upgradeModule = CustomItemBuilder.getWandUpgrade(config.getConfiguration().getString(event.getCurrentItem().getItemMeta().getLocalizedName() + ".Upgrade_2"));
                                inventory.setItem(41, new CustomItemBuilder(upgradeModule).createUpgradeModule());
                            }
                        }else {
                            inventory.setItem(41, new ItemBuilder(Material.BARRIER).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fNot unlocked yet").build());
                        }
                    }else {
                        inventory.setItem(39, new ItemBuilder(Material.BARRIER).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fNot unlocked yet").build());
                        inventory.setItem(41, new ItemBuilder(Material.BARRIER).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName("§fNot unlocked yet").build());
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
                ItemStack result = null;
                boolean success = false;
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
                if (checkIfItem(itemStacks, ItemManager.tier_2_wand)){
                    result = new CustomItemBuilder(CustomItems.WAND_TIER_2).build();
                    int ri = (int) (Math.random()*100000);
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_1", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_1"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_2", "Open");
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + "Spell_3", "Locked");
                    Main.getPlugin().getWandFile().save();
                    ItemMeta meta = result.getItemMeta();
                    meta.setLocalizedName(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri);
                    result.setItemMeta(meta);
                    success = true;
                }
                if (checkIfItem(itemStacks, ItemManager.tier_3_wand)){
                    result = new CustomItemBuilder(CustomItems.WAND_TIER_3).build();
                    int ri = (int) (Math.random()*100000);
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_1", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_1"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_2", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_2"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_3", "Open");
                    Main.getPlugin().getWandFile().save();
                    ItemMeta meta = result.getItemMeta();
                    meta.setLocalizedName(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri);
                    result.setItemMeta(meta);
                    success = true;
                }
                if (checkIfItem(itemStacks, ItemManager.tier_4_wand)){
                    result = new CustomItemBuilder(CustomItems.WAND_TIER_4).build();
                    int ri = (int) (Math.random()*100000);
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_1", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_1"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_2", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_2"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_3", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_3"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Upgrade_1", "Open");
                    Main.getPlugin().getWandFile().save();
                    ItemMeta meta = result.getItemMeta();
                    meta.setLocalizedName(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri);
                    result.setItemMeta(meta);
                    success = true;
                }
                if (checkIfItem(itemStacks, ItemManager.tier_5_wand)){
                    result = new CustomItemBuilder(CustomItems.WAND_TIER_5).build();
                    int ri = (int) (Math.random()*100000);
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_1", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_1"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_2", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_2"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Spell_3", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Spell_3"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Upgrade_1", Main.getPlugin().getWandFile().get(itemStacks[1].getItemMeta().getLocalizedName() + ".Upgrade_1"));
                    Main.getPlugin().getWandFile().set(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri + ".Upgrade_2", "Open");
                    Main.getPlugin().getWandFile().save();
                    ItemMeta meta = result.getItemMeta();
                    meta.setLocalizedName(result.getItemMeta().getLocalizedName() + "." + event.getWhoClicked().getUniqueId() + "." + ri);
                    result.setItemMeta(meta);
                    success = true;
                }
                if (success){
                    BasicUtility.giveItemSavely(event.getWhoClicked(), result);
                    event.getClickedInventory().setItem(19, null);
                    event.getClickedInventory().setItem(22, null);
                    event.getClickedInventory().setItem(25, null);
                    event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 2, 5);
                    event.getWhoClicked().closeInventory();
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
            event.getWhoClicked().openInventory(createGui(title_first));
            event.getWhoClicked().closeInventory();
        }
        if (CustomItemBuilder.getSpell(event.getCurrentItem().getItemMeta().getLocalizedName())!=null) {
        } else if (CustomItemBuilder.getWandUpgrade(event.getCurrentItem().getItemMeta().getLocalizedName())!=null){
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
            lore.add("§7Spells:");
            if (inventory.getItem(38)!=null){
                if (inventory.getItem(38).getType().equals(Material.AIR)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Open");
                    lore.add("§7[§8Open§7]");
                } else if (inventory.getItem(38).getType().equals(Material.BARRIER)) {
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Locked");
                    lore.add("§7[§cLocked§7]");
                }else {
                    if (inventory.getItem(38).getItemMeta().getLocalizedName().contains("Upgrade")){
                        BasicUtility.giveItemSavely(event.getPlayer(), inventory.getItem(38));
                        event.getPlayer().sendMessage("§cYou cannot put an upgrade module into a spell field.");
                        lore.add("§7[§8Open§7]");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Open");
                    }else {
                        Spell spell = CustomItemBuilder.getSpell(inventory.getItem(38).getItemMeta().getLocalizedName());
                        if (spell.getTier() > CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())) {
                            event.getPlayer().getInventory().addItem(inventory.getItem(38));
                            event.getPlayer().sendMessage("§cThe spell had a higher tier than your wand.");
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", "Open");
                        } else {
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_1", inventory.getItem(38).getItemMeta().getLocalizedName());
                            lore.add("§7[" + spell.getColor() + spell.getName() + "§7]");
                        }
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
                    if (inventory.getItem(40).getItemMeta().getLocalizedName().contains("Upgrade")){
                        BasicUtility.giveItemSavely(event.getPlayer(), inventory.getItem(40));
                        event.getPlayer().sendMessage("§cYou cannot put an upgrade module into a spell field.");
                        lore.add("§7[§8Open§7]");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", "Open");
                    }
                    else {
                        Spell spell = CustomItemBuilder.getSpell(inventory.getItem(40).getItemMeta().getLocalizedName());
                        if (spell.getTier() > CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())) {
                            event.getPlayer().getInventory().addItem(inventory.getItem(40));
                            event.getPlayer().sendMessage("§cThe spell had a higher tier than your wand.");
                            lore.add("§7[§8Open§7]");
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", "Open");
                        } else {
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_2", inventory.getItem(40).getItemMeta().getLocalizedName());
                            lore.add("§7[" + spell.getColor() + spell.getName() + "§7]");
                        }
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
                    if (inventory.getItem(42).getItemMeta().getLocalizedName().contains("Upgrade")){
                        BasicUtility.giveItemSavely(event.getPlayer(), inventory.getItem(42));
                        event.getPlayer().sendMessage("§cYou cannot put an upgrade module into a spell field.");
                        lore.add("§7[§8Open§7]");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Open");
                    }else {
                        Spell spell = CustomItemBuilder.getSpell(inventory.getItem(42).getItemMeta().getLocalizedName());
                        if (spell.getTier() > CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())) {
                            event.getPlayer().getInventory().addItem(inventory.getItem(42));
                            event.getPlayer().sendMessage("§cThe spell had a higher tier than your wand.");
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Open");
                        } else {
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", inventory.getItem(42).getItemMeta().getLocalizedName());
                            lore.add("§7[" + spell.getColor() + spell.getName() + "§7]");
                        }
                    }
                }
            }else {
                Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Spell_3", "Open");
                lore.add("§7[§8Open§7]");
            }
            lore.add("§7Upgrade Modules:");
            if (inventory.getItem(39)!=null){
                if (inventory.getItem(39).getType().equals(Material.AIR)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_1", "Open");
                    lore.add("§7[§8Open§7]");
                }else if (inventory.getItem(39).getType().equals(Material.BARRIER)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_1", "Locked");
                    lore.add("§7[§cLocked§7]");
                }else {
                    if (inventory.getItem(39).getItemMeta().getLocalizedName().contains("Spell")){
                        BasicUtility.giveItemSavely(event.getPlayer(), inventory.getItem(39));
                        event.getPlayer().sendMessage("§cYou cannot put spell into an upgrade module field.");
                        lore.add("§7[§8Open§7]");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_1", "Open");
                    }else {
                        WandUpgradeModule wandUpgradeModule = CustomItemBuilder.getWandUpgrade(inventory.getItem(39).getItemMeta().getLocalizedName());
                        if (wandUpgradeModule.getTier() > CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())) {
                            event.getPlayer().getInventory().addItem(inventory.getItem(39));
                            event.getPlayer().sendMessage("§cThe upgrade module  had a higher tier than your wand.");
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_1", "Open");
                        } else {
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_1", inventory.getItem(39).getItemMeta().getLocalizedName());
                            lore.add("§7[" + wandUpgradeModule.getName() + "§7]");
                        }
                    }
                }
            }else {
                Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_1", "Open");
                lore.add("§7[§8Open§7]");
            }
            if (inventory.getItem(41)!=null){
                if (inventory.getItem(41).getType().equals(Material.AIR)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_2", "Open");
                    lore.add("§7[§8Open§7]");
                }else if (inventory.getItem(41).getType().equals(Material.BARRIER)){
                    Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_2", "Locked");
                    lore.add("§7[§cLocked§7]");
                }else {
                    if (inventory.getItem(41).getItemMeta().getLocalizedName().contains("Spell")){
                        BasicUtility.giveItemSavely(event.getPlayer(), inventory.getItem(41));
                        event.getPlayer().sendMessage("§cYou cannot put spell into an upgrade module field.");
                        lore.add("§7[§8Open§7]");
                        Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_2", "Open");
                    }else {
                        WandUpgradeModule wandUpgradeModule = CustomItemBuilder.getWandUpgrade(inventory.getItem(41).getItemMeta().getLocalizedName());
                        if (wandUpgradeModule.getTier() > CustomItemBuilder.getTier(inventory.getItem(13).getItemMeta().getLocalizedName())) {
                            event.getPlayer().getInventory().addItem(inventory.getItem(41));
                            event.getPlayer().sendMessage("§cThe upgrade module  had a higher tier than your wand.");
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_2", "Open");
                        } else {
                            Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_2", inventory.getItem(41).getItemMeta().getLocalizedName());
                            lore.add("§7[" + wandUpgradeModule.getName() + "§7]");
                        }
                    }
                }
            }else {
                Main.getPlugin().getWandFile().set(inventory.getItem(13).getItemMeta().getLocalizedName() + ".Upgrade_2", "Open");
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
        }else if (event.getView().getTitle().equals(upgrade_Title)){
            if (event.getInventory().getItem(19)!=null){
                BasicUtility.giveItemSavely(event.getPlayer(), event.getInventory().getItem(19));
            }
            if (event.getInventory().getItem(22)!=null){
                BasicUtility.giveItemSavely(event.getPlayer(), event.getInventory().getItem(22));
            }
            if (event.getInventory().getItem(25)!=null){
                BasicUtility.giveItemSavely(event.getPlayer(), event.getInventory().getItem(25));
            }
        }
    }

    public boolean checkIfItem(ItemStack[] craftingItems, ItemStack[] crafting_recipe){
        if (craftingItems[0].getItemMeta().getLocalizedName().contains(crafting_recipe[0].getItemMeta().getLocalizedName())) {
            if (craftingItems[1].getItemMeta().getLocalizedName().contains(crafting_recipe[1].getItemMeta().getLocalizedName())) {
                if (craftingItems[2].equals(crafting_recipe[2])) {
                    return true;
                }
            }
        }
        return false;
    }



}

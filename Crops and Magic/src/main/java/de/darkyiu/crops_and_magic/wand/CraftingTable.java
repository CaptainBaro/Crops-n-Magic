package de.darkyiu.crops_and_magic.wand;


import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.util.BasicUtility;
import de.darkyiu.crops_and_magic.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class CraftingTable implements Listener {

    public String title = "§7Craft:";

    public Inventory createCraftingTable(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, title);
        for (int i = 0;i<27; i++){
            inventory.setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLocalizedName("Gui.Element").setName(" ").build());
        }
        inventory.setItem(3 ,new ItemStack(Material.AIR));
        inventory.setItem(4, new ItemStack(Material.AIR));
        inventory.setItem(5, new ItemStack(Material.AIR));
        inventory.setItem(12, new ItemStack(Material.AIR));
        inventory.setItem(13, new ItemStack(Material.AIR));
        inventory.setItem(14, new ItemStack(Material.AIR));
        inventory.setItem(16,  new ItemBuilder(Material.LIME_DYE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLocalizedName("Gui.Craft").setName("§aCraft").build());
        inventory.setItem(21, new ItemStack(Material.AIR));
        inventory.setItem(22, new ItemStack(Material.AIR));
        inventory.setItem(23, new ItemStack(Material.AIR));
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(title))return;
        if (event.getCurrentItem()==null)return;
        if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Element")){
            event.setCancelled(true);
        } else if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("Gui.Craft")) {
            event.setCancelled(true);
            ItemStack[] itemStacks = new ItemStack[9];
            if (event.getClickedInventory().getItem(3)!=null){
                itemStacks[0] = event.getClickedInventory().getItem(3);
            }else {
                itemStacks[0] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(4)!=null){
                 itemStacks[1] = event.getClickedInventory().getItem(4);
            }else {
                 itemStacks[1] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(5)!=null){
                 itemStacks[2] = event.getClickedInventory().getItem(5);
            }else {
                 itemStacks[2] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(12)!=null){
                 itemStacks[3] = event.getClickedInventory().getItem(12);
            }else {
                 itemStacks[3] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(13)!=null){
                 itemStacks[4] = event.getClickedInventory().getItem(13);
            }else {
                 itemStacks[4] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(14)!=null){
                 itemStacks[5] = event.getClickedInventory().getItem(14);
            }else {
                 itemStacks[5] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(21)!=null){
                 itemStacks[6] = event.getClickedInventory().getItem(21);
            }else {
                 itemStacks[6] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(22)!=null){
                itemStacks[7] = event.getClickedInventory().getItem(22);
            }else {
                itemStacks[7] = new ItemStack(Material.AIR);
            }
            if (event.getClickedInventory().getItem(23)!=null){
                 itemStacks[8] = event.getClickedInventory().getItem(23);
            }else {
                 itemStacks[8] = new ItemStack(Material.AIR);
            }
            boolean sucess = false;
            if (checkItem(itemStacks, ItemManager.obsidian_shaft)){
                BasicUtility.giveItemSavely(event.getWhoClicked(), ItemManager.obsidian_shaft[9]);
                sucess = true;
            }
            if (sucess){
                event.getWhoClicked().closeInventory();
            }else {
                event.getWhoClicked().sendMessage("§cWrong materials");
            }

        }
    }

    public boolean checkItem(ItemStack[] craftItems, ItemStack[] craftingRecipe){
        boolean sucess = true;
        for (int i = 0; i < 9; i++){
            if (!craftItems[0].equals(craftingRecipe[0])){
                sucess = false;
            }
        }
        return sucess;
    }

}

package de.darkyiu.crops_and_magic.custom_crafting;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItemBuilder {

    private final ItemStack itemStack;
    private ItemMeta meta;
    private CustomItems customItems;

    public CustomItemBuilder(CustomItems customItems){
        itemStack = new ItemStack(customItems.getMaterial(), 1);
        this.customItems = customItems;
        meta = itemStack.getItemMeta();
    }

    public ItemStack build(){
        meta.setDisplayName(customItems.getName());
        meta.setCustomModelData(customItems.getCustomModelData());
        if (customItems.isEnchanted()){
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        List<String> lore = new ArrayList<>();
        meta.setLore(cutLore(customItems.getLore()));
        if (customItems.getLocalizedName()!=null){
            meta.setLocalizedName(customItems.getLocalizedName());
        }
        if (customItems.isUnbreakable()){
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ArrayList<String> cutLore(String lore){
        int end = -1;
        ArrayList<String> lores = new ArrayList<String>();
        for (int start = 22; start<lore.length(); start++){
            if(lore.charAt(start) == (char) 32){
                lores.add(lore.substring(end+1, start));
                end = start;
                start = start + 23;
            }
        }
        lores.add("§7"+ lore.substring(end+1));

        return lores;

    }

}

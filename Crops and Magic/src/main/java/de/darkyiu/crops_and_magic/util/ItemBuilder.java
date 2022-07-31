package de.darkyiu.crops_and_magic.util;


import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material, short subID){
        item = new ItemStack(material, 1, subID);
        itemMeta = item.getItemMeta();
    }
    public ItemBuilder(Material material){
        this(material, (short)0);
    }

    public ItemBuilder setName(String name){
        itemMeta.setDisplayName(name);
        return this;
    }
    public ItemBuilder setLore(String... lore){
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder setAmount(int amount){
        item.setAmount(amount);
        return this;
    }
    public ItemBuilder setModelData(int modelData){
        itemMeta.setCustomModelData(modelData);
        return this;
    }
    public ItemBuilder addAtribute(Attribute attribute, AttributeModifier attributeModifier){
        itemMeta.addAttributeModifier(attribute, attributeModifier);
        return this;
    }
    public ItemBuilder addItemFlags(ItemFlag itemFlag){
        itemMeta.addItemFlags(itemFlag);
        return this;
    }
    public ItemBuilder setLocalizedName(String localizedName){
        itemMeta.setLocalizedName(localizedName);
        return this;
    }
    public ItemBuilder addEnchant(Enchantment enchantment, int i){
        itemMeta.addEnchant(enchantment, i, true);
        return this;
    }
    public ItemStack build(){
        item.setItemMeta(itemMeta);
        return item;
    }

}

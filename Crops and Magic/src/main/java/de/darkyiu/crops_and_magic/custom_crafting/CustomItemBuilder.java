package de.darkyiu.crops_and_magic.custom_crafting;

import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.crops.FarmingCrops;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.spells.Spell;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomItemBuilder {

    private ItemStack itemStack;
    private ItemMeta meta;
    private CustomItems customItems;
    private Crop crop;
    private Spell spell;
    private Relic relic;
    private CustomFood customFood;

    public CustomItemBuilder(CustomFood customFood){
        itemStack = new ItemStack(customFood.getMaterial(), 1);
        this.customFood = customFood;
        meta = itemStack.getItemMeta();
    }

    public CustomItemBuilder(CustomItems customItems){
        itemStack = new ItemStack(customItems.getMaterial(), 1);
        this.customItems = customItems;
        meta = itemStack.getItemMeta();
    }

    public CustomItemBuilder(Relic relic){
        itemStack = new ItemStack( Material.STRUCTURE_VOID,1);
        meta = itemStack.getItemMeta();
        this.relic = relic;
    }
    public CustomItemBuilder(Crop crop){
        this.crop = crop;
    }

    public CustomItemBuilder(Spell spell){
        this.spell = spell;
    }




    public ItemStack build(){
        meta.setDisplayName(customItems.getName());
        meta.setCustomModelData(customItems.getCustomModelData());
        if (customItems.isEnchanted()){
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (customItems.getLore()!=null){
            List<String> lore = new ArrayList<>();
            meta.setLore(cutLore(customItems.getLore()));
        }
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

    public ItemStack createFood(){
        meta.setDisplayName(customFood.getName());
        meta.setLocalizedName(customFood.getLocalizedName());
        if (customFood.getLore()!=null){
            meta.setLore(cutLore(customFood.getLore()));
        }
        meta.setCustomModelData(customFood.getCustomModelData());
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

    public ItemStack createEatingCrop(Crop crop){
        this.crop = crop;
        itemStack = new ItemStack(Material.SWEET_BERRIES);
        meta = itemStack.getItemMeta();
        meta.setDisplayName(crop.getName());
        meta.setCustomModelData(crop.getModelEat());
        meta.setLocalizedName(crop.getLocalized_Eat());
        if (crop.getLore() != null){
            meta.setLore(cutLore(crop.getLore()));
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createRelic(){
        meta.setDisplayName(relic.getName());
        meta.setLocalizedName(relic.getLocalizedName());
        meta.setCustomModelData(relic.getCustomModelData());
        List<String>  lore = new ArrayList<>();
        if(relic.getLore() != null){
            lore.add("");
            ArrayList<String> lores = cutLore(relic.getLore());
            for (String addLore : lores){
                lore.add("§7" + addLore);
            }
        }
        lore.add(ChatColor.LIGHT_PURPLE + "§lLOST RELIC");
        meta.setLore(lore);
        if (relic.isEnchanted()){
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (relic.isUnbreakable()){
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        itemStack.setItemMeta(meta);
        return  itemStack;
    }

    public ItemStack createFarmingCrop(Crop crop,int stage){
        itemStack = new ItemStack(Material.PUMPKIN_SEEDS);
        meta = itemStack.getItemMeta();
        switch (stage){
            case 1:
                meta.setLocalizedName(crop.getLocalized_Farm_1());
                meta.setCustomModelData(crop.getModel_Farm_1());
                meta.setDisplayName(crop.getName() + "  " + stage);
                itemStack.setItemMeta(meta);
                return itemStack;
            case 2:
                meta.setLocalizedName(crop.getLocalized_Farm_2());
                meta.setCustomModelData(crop.getModel_Farm_2());
                meta.setDisplayName(crop.getName() + "  " + stage);
                itemStack.setItemMeta(meta);
                return itemStack;
            case 3:
                meta.setLocalizedName(crop.getLocalized_Farm_3());
                meta.setCustomModelData(crop.getModel_Farm_3());
                meta.setDisplayName(crop.getName() + "  " + stage);
                itemStack.setItemMeta(meta);
                return itemStack;
        }
        return itemStack;
    }

    public ItemStack createSpell(){
        itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        meta = itemStack.getItemMeta();
        meta.setDisplayName(spell.getColor() + spell.getName());
        meta.setLocalizedName(spell.getLocalizedName());
        List<String>  lore = new ArrayList<>();
        if(spell.getLore() != null){
            lore.add("");
            ArrayList<String> lores = cutLore(spell.getLore());
            for (String addLore : lores){
                lore.add("§7" + addLore);
            }
        }
        lore.add(spell.getColor() + "§lTIER " + spell.getTier() + " SPELL");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static Crop getFarmingCrop(ItemStack itemStack){
        if (itemStack.getItemMeta()==null)return null;
        if (itemStack.getItemMeta().getLocalizedName()==null)return null;
        for(Crop farmingCrop : Crop.values()){
            if (farmingCrop.getLocalized_Farm_1().equalsIgnoreCase(itemStack.getItemMeta().getLocalizedName())){
                return farmingCrop;
            }else if (farmingCrop.getLocalized_Farm_2().equalsIgnoreCase(itemStack.getItemMeta().getLocalizedName())) {
                return farmingCrop;
            }else if (farmingCrop.getLocalized_Farm_3().equalsIgnoreCase(itemStack.getItemMeta().getLocalizedName())) {
                return farmingCrop;
            }
        }
        return null;
    }
    public static Crop getEatingCrops(ItemStack itemStack){
        if (itemStack.getItemMeta()==null)return null;
        if (itemStack.getItemMeta().getLocalizedName()==null)return null;
        for(Crop crops : Crop.values()){
            if (crops.getLocalized_Eat().equalsIgnoreCase(itemStack.getItemMeta().getLocalizedName())){
                return crops;
            }
        }
        return null;
    }

    public static CustomFood getCustomFood(ItemStack itemStack) {
        for(CustomFood customFood1 : CustomFood.values()){
            if (customFood1.getLocalizedName().equalsIgnoreCase(itemStack.getItemMeta().getLocalizedName())){
                return customFood1;
            }
        }
        return null;
    }
    public static CustomItems getCustomItem(String string) {
        for(CustomItems customFood1 : CustomItems.values()){
            if (string.contains(customFood1.getLocalizedName())){
                return customFood1;
            }
        }
        return null;
    }

    public static Spell getSpell(String string){
        for(Spell spell : Spell.values()){
            if (spell.getLocalizedName().equalsIgnoreCase(string)){
                return spell;
            }
        }
        return null;
    }

    public static Relic getRelic(String string){
        for(Relic relic1 : Relic.values()){
            if (relic1.getLocalizedName().equalsIgnoreCase(string)){
                return relic1;
            }
        }
        return null;
    }
    public static Crop getCrop(String string){
        for(Crop crop1 : Crop.values()){
            if (crop1.getLocalized_Eat().equalsIgnoreCase(string)){
                return crop1;
            }
        }
        return null;
    }

    public static int getTier(String string){
        if (string.contains("Item.Wand.1")){
            return 1;
        }else if (string.contains("Item.Wand.2")){
            return 2;
        }else if (string.contains("Item.Wand.3")){
            return 3;
        }else if (string.contains("Item.Wand.4")){
            return 4;
        }else if (string.contains("Item.Wand.5")){
            return 5;
        }else return 0;
    }

    public static int getStage(ItemStack itemStack){
        if (itemStack.getItemMeta()==null)return 0;
        if (itemStack.getItemMeta().getLocalizedName()==null)return 0;
        for(FarmingCrops farmingCrop : FarmingCrops.values()){
            if (farmingCrop.getLocalizedName().equalsIgnoreCase(itemStack.getItemMeta().getLocalizedName())){
                if (farmingCrop.getLocalizedName().contains("1")){
                    return 1;
                }else if (farmingCrop.getLocalizedName().contains("2")){
                    return 2;
                }if (farmingCrop.getLocalizedName().contains("3")){
                    return 3;
                }
            }
        }
        return 0;
    }
}

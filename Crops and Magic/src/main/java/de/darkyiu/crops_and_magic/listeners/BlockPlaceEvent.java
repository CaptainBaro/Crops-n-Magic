package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.wand.WandAssemblyTable;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceEvent implements Listener {

    @EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent event) {
         if (event.getItemInHand().getItemMeta()==null)return;
         if (event.getItemInHand().getItemMeta().getLocalizedName().contains("Block_Item")){
             CustomItems customItems = CustomItemBuilder.getCustomItem(event.getItemInHand().getItemMeta().getLocalizedName());
             event.setCancelled(true);
             switch (customItems){
                 case MAGICAL_WOOD:break;
                 case SPELL_ASSEMBLE_BLOCK_ITEM:
                     ItemFrame itemFrame = (ItemFrame) event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.ITEM_FRAME);
                     itemFrame.setSilent(true);
                     itemFrame.setVisible(false);
                     itemFrame.setFacingDirection(BlockFace.UP);
                     itemFrame.setItem(new CustomItemBuilder(CustomItems.SPELL_ASSEMBLE_BLOCK).build());
                     ItemStack itemStack = event.getItemInHand();
                     itemStack.setAmount(itemStack.getAmount()-1);
                     event.getPlayer().getInventory().remove(event.getItemInHand());
                     event.getPlayer().getInventory().addItem(itemStack);
                     break;
             }
         }
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getType().equals(EntityType.ITEM_FRAME)){
            ItemFrame itemFrame = (ItemFrame) event.getRightClicked();
            if (itemFrame.getItem().getItemMeta()!=null){
                if (CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName()) != null){
                    event.setCancelled(true);
                    CustomItems customItems = CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName());
                    if (customItems==CustomItems.SPELL_ASSEMBLE_BLOCK){
                        event.getPlayer().openInventory(WandAssemblyTable.createGui(WandAssemblyTable.title_first));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType().equals(EntityType.ITEM_FRAME)){
            ItemFrame itemFrame = (ItemFrame) event.getEntity();
            if (itemFrame.getItem().getItemMeta()!=null) {
                if (CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName()) != null) {
                    event.setCancelled(true);
                    CustomItems customItems = CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName());
                    if (customItems == CustomItems.SPELL_ASSEMBLE_BLOCK) {
                        itemFrame.getLocation().getWorld().dropItemNaturally(itemFrame.getLocation(),new CustomItemBuilder(CustomItems.SPELL_ASSEMBLE_BLOCK_ITEM).build());
                    }
                    itemFrame.setItem(new ItemStack(Material.AIR));
                    itemFrame.remove();
                }
            }
        }


    }
}

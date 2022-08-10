package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItems;
import de.darkyiu.crops_and_magic.wand.WandAssemblyTable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class BlockPlaceEvent implements Listener {

    @EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent event) {
         if (event.getItemInHand().getItemMeta()==null)return;
         if (event.getItemInHand().getItemMeta().getLocalizedName().contains("Block_Item") ||event.getItemInHand().getItemMeta().getLocalizedName().contains("Item.")){
             CustomItems customItems = CustomItemBuilder.getCustomItem(event.getItemInHand().getItemMeta().getLocalizedName());
             switch (customItems){
                 case SPELL_ASSEMBLE_BLOCK_ITEM:
                     ItemFrame itemFrame = (ItemFrame) event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.ITEM_FRAME);
                     itemFrame.setSilent(true);
                     itemFrame.setVisible(true);
                     itemFrame.setInvulnerable(true);
                     itemFrame.setFixed(true);
                     itemFrame.setItem(new CustomItemBuilder(CustomItems.SPELL_ASSEMBLE_BLOCK).build());
                     Main.getPlugin().getBlockList().add(itemFrame.getUniqueId().toString());
                     itemFrame.setFacingDirection(BlockFace.UP, true);
                     itemFrame.getWorld().setType(itemFrame.getLocation(), Material.GLASS);
                     itemFrame.getLocation().getBlock().setType(Material.GLASS);
                     ItemStack itemStack = event.getItemInHand();
                     itemStack.setAmount(itemStack.getAmount()-1);
                     event.getPlayer().getInventory().remove(event.getItemInHand());
                     event.getPlayer().getInventory().addItem(itemStack);
                     break;
                 default:
                     event.setCancelled(true);
                     break;
             }
         }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractAtEntity(PlayerInteractEvent event) {
        if (event.getClickedBlock()==null)return;
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (event.getClickedBlock().getType().equals(Material.GLASS)){
                for (String uuid : Main.getPlugin().getBlockList()){
                    Entity entity = Bukkit.getEntity(UUID.fromString(uuid));
                    if (entity!=null&&entity.getType().equals(EntityType.ITEM_FRAME)){
                        if (entity.getLocation().distance(event.getClickedBlock().getLocation().add(0.35, 0, 0.35))<0.3){
                            ItemFrame itemFrame = (ItemFrame) entity;
                            CustomItems customItems = CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName());
                            if (customItems.equals(CustomItems.SPELL_ASSEMBLE_BLOCK)){
                                event.getPlayer().openInventory(WandAssemblyTable.createGui(WandAssemblyTable.title_first));
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void removeItemFrame(ItemFrame itemFrame) {
            if (itemFrame.getItem().getItemMeta()!=null) {
                if (CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName()) != null) {
                    CustomItems customItems = CustomItemBuilder.getCustomItem(itemFrame.getItem().getItemMeta().getLocalizedName());
                    if (customItems == CustomItems.SPELL_ASSEMBLE_BLOCK) {
                        itemFrame.getLocation().getWorld().dropItemNaturally(itemFrame.getLocation(),new CustomItemBuilder(CustomItems.SPELL_ASSEMBLE_BLOCK_ITEM).build());
                    }
                    itemFrame.setItem(new ItemStack(Material.AIR));
                    itemFrame.remove();
                    Main.getPlugin().getBlockList().remove(itemFrame.getUniqueId().toString());
                }
            }
    }

}

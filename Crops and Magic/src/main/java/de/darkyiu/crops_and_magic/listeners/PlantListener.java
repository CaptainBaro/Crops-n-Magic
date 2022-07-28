package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.crops.Crop;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.Map;

public class PlantListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if (event.getClickedBlock()==null)return;
        if (event.getClickedBlock().getType() == (Material.FARMLAND)){
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                if (event.getItem()==null)return;
                if (event.getItem().getItemMeta().getLocalizedName()!=null){
                    if (CustomItemBuilder.getEatingCrops(event.getItem())!=null){
                        event.setCancelled(true);
                        Crop crop = CustomItemBuilder.getEatingCrops(event.getItem());
                        Location targetLocation = event.getClickedBlock().getLocation().add(0,1,0);
                        ItemFrame entity = (ItemFrame) targetLocation.getWorld().spawnEntity(targetLocation, EntityType.ITEM_FRAME);
                        entity.setSilent(true);
                        Main.getPlugin().getFrameList().add(entity.getUniqueId().toString());
                        entity.setVisible(false);
                        entity.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop,1));
                        event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                entity.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 2));
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if (CustomItemBuilder.getStage(entity.getItem())>2){
                                            entity.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 3));
                                        }else {
                                            entity.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, CustomItemBuilder.getStage(entity.getItem())+1));
                                        }
                                    }
                                }.runTaskLater(Main.getPlugin(), 20*10);
                            }
                        }.runTaskLater(Main.getPlugin(),20*10);
                    }
                }
            }
        }else {
            if (event.getItem()==null)return;
            if (event.getItem().getItemMeta().getLocalizedName()!=null) {
                if (CustomItemBuilder.getEatingCrops(event.getItem()) != null) {
                    event.setCancelled(true);
                }
            }
        }

        Date date = new Date();
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if (event.getEntity().getType().equals(EntityType.ITEM_FRAME)){
            ItemFrame itemFrame = (ItemFrame) event.getEntity();
            if (!(itemFrame.getItem().getType().equals(Material.AIR))){
                if (itemFrame.getItem().getItemMeta().getLocalizedName()!=null){
                    if(CustomItemBuilder.getFarmingCrop(itemFrame.getItem())!=null){
                        Crop crop = CustomItemBuilder.getFarmingCrop(itemFrame.getItem());
                        int stage = CustomItemBuilder.getStage(itemFrame.getItem());
                        itemFrame.setItem(new ItemStack(Material.AIR));
                        event.getDamager().getWorld().playSound(event.getDamager().getLocation(), Sound.BLOCK_CROP_BREAK, 4, 0);
                        Main.getPlugin().getFrameList().remove(itemFrame.getUniqueId().toString());
                        itemFrame.remove();
                        switch (stage){
                            case 1:
                                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(Crop.STRAWBERRY).createEatingCrop(crop));
                                break;
                            case 2:
                                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(Crop.STRAWBERRY).createEatingCrop(crop));
                                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(Crop.STRAWBERRY).createEatingCrop(crop));
                                break;
                            case 3:
                                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(crop).createEatingCrop(crop));
                                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(crop).createEatingCrop(crop));
                                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(crop).createEatingCrop(crop));
                                break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        if (event.getRightClicked().getType()!=EntityType.ITEM_FRAME)return;
        ItemFrame itemFrame = (ItemFrame) event.getRightClicked();
        if (itemFrame.getItem().getType().equals(Material.AIR))return;
        if (itemFrame.getItem().getItemMeta().getLocalizedName()==null)return;
        if (CustomItemBuilder.getFarmingCrop(itemFrame.getItem())==null)return;
        event.setCancelled(true);
        Crop crop = CustomItemBuilder.getFarmingCrop(itemFrame.getItem());
        int stage = CustomItemBuilder.getStage(itemFrame.getItem());
        switch (stage){
            case 1:
                break;
            case 2:
                player.playSound(player.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 3f, 5);
                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(crop).createEatingCrop(crop));
                itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 1));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 2));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (CustomItemBuilder.getStage(itemFrame.getItem())>2){
                                    itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 3));
                                }else {
                                    itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, CustomItemBuilder.getStage(itemFrame.getItem())+1));
                                }
                            }
                        }.runTaskLater(Main.getPlugin(), 20*10);
                    }
                }.runTaskLater(Main.getPlugin(),20*10);
                break;
            case 3:
                player.playSound(player.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 3f, 5);
                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(crop).createEatingCrop(crop));
                itemFrame.getWorld().dropItemNaturally(itemFrame.getLocation(), new CustomItemBuilder(crop).createEatingCrop(crop));
                itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 1));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 2));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (CustomItemBuilder.getStage(itemFrame.getItem())>2){
                                    itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, 3));
                                }else {
                                    itemFrame.setItem(new CustomItemBuilder(crop).createFarmingCrop(crop, CustomItemBuilder.getStage(itemFrame.getItem())+1));
                                }

                            }
                        }.runTaskLater(Main.getPlugin(), 20*10);
                    }
                }.runTaskLater(Main.getPlugin(),20*10);
                break;
        }

    }

}

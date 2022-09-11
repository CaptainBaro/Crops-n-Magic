package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import de.darkyiu.crops_and_magic.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class DiscoveryRelic implements RelicAbility, Listener {

    public Location location;

    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        Inventory teleportInv = Bukkit.createInventory(null, 9 * 4, "§aPlayer Teleporter");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            teleportInv.addItem(getItem(onlinePlayer));
        }
        player.openInventory(teleportInv);
    }

    @Override
    public void onEvent(EntityDeathEvent event) {

    }

    public static ItemStack getItem(Player player){
        ItemStack itemStack = new ItemBuilder(Material.PLAYER_HEAD).setName("§a" + player.getName()).setLore("§aKlicke um dich zu diesem Spieler zu teleportieren!").build();
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        skullMeta.setLocalizedName(String.valueOf(player.getName()));
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("§aPlayer Teleporter")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getItemMeta()==null)return;
                String playerName = event.getCurrentItem().getItemMeta().getLocalizedName();
                event.getWhoClicked().closeInventory();
                Player player = Bukkit.getPlayer(playerName);
                discoverPlayer((Player) event.getWhoClicked(), player);
            }
        }
    }

    public void discoverPlayer(Player player, Player discoverer){
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5, 0));
        location = player.getLocation();
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(discoverer);
                List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                for (Player player1 : players){
                    player1.hidePlayer(Main.getPlugin(), player);
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*10, 0));
                player.getWorld().setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.teleport(player.getLocation());
                        for (Player player1 : players){
                            player1.showPlayer(Main.getPlugin(), player);
                        }
                        player.getWorld().setGameRule(GameRule.REDUCED_DEBUG_INFO, false);
                    }
                }.runTaskLater(Main.getPlugin(), 20*10);
            }
        }.runTaskLater(Main.getPlugin(), 20*3);
    }
}

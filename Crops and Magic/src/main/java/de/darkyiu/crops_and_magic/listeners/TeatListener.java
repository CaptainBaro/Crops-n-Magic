package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.custom_crafting.ItemManager;
import de.darkyiu.crops_and_magic.spells.Spell;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class TeatListener implements Listener {

    @EventHandler
    public void onTest(PlayerJoinEvent event){
        event.getPlayer().sendMessage("§atest");
        for (NamespacedKey key : ItemManager.recipes){
            event.getPlayer().discoverRecipe(key);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())return;
                if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLocalizedName())return;
                if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLocalizedName().contains("Item.Wand.")){
                    return;
                }
                if (Main.getPlugin().getWandSpellHashmap().getSpell(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLocalizedName())==0)return;
                String spellname = Main.getPlugin().getWandFile().getConfiguration().getString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLocalizedName() + ".Spell_" + Main.getPlugin().getWandSpellHashmap().getSpell(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLocalizedName()));
                if (spellname.equalsIgnoreCase("open")|| spellname.equalsIgnoreCase("locked")){
                    return;
                }
                Spell spell = CustomItemBuilder.getSpell(spellname);
                event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(spell.getColor() + spell.getName()));
            }
        }.runTaskTimer(Main.getPlugin(), 0, 1);
    }

    @EventHandler
    public void onEntityCombustByBlock(EntityCombustByBlockEvent event) {
        if(event.getEntity().getType().equals(EntityType.DROPPED_ITEM)){
            Item item = (Item) event.getEntity();
            if (item.getItemStack().getItemMeta()==null)return;
            if (item.getItemStack().getItemMeta().getLocalizedName().contains("Item.Wand.")){
                Objects.requireNonNull(event.getEntity().getLocation().getWorld()).createExplosion(event.getEntity().getLocation(), 4);
            }
        }

    }
}

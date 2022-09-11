package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity().getType() != EntityType.PLAYER)return;
        Player player = (Player) event.getEntity();
        Main.getPlugin().data.addDeaths(player.getUniqueId(), 1);
        player.sendMessage("§cDeath added!");
    }
}

package de.darkyiu.crops_and_magic.listeners;

import de.darkyiu.crops_and_magic.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class AnimalKillLIstener implements Listener {


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if(event.getEntity().getKiller() instanceof Player){
            Player player = (Player) event.getEntity().getKiller();
            Main.getPlugin().data.addPoints(player.getUniqueId(), 1);
            player.sendMessage("§aPoints added!");
        }

    }
}

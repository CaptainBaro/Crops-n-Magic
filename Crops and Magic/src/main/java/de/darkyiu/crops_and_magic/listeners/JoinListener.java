package de.darkyiu.crops_and_magic.listeners;


import de.darkyiu.crops_and_magic.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Main.getPlugin().data.createPLayer(player);
    }
}

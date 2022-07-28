package de.darkyiu.crops_and_magic.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TeatListener implements Listener {

    public void onTest(PlayerJoinEvent event){
        event.getPlayer().sendMessage("§atest");
    }

}

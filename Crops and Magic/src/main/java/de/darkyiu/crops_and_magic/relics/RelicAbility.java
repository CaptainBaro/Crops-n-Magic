package de.darkyiu.crops_and_magic.relics;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public interface RelicAbility  {

    public void onLeftClick(Player player, Relic relic);

    public void onRightClick(Player player, Relic relic);

    @EventHandler
    public void onEvent(EntityDeathEvent event);


}

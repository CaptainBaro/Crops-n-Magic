package de.darkyiu.crops_and_magic.relics;

import org.bukkit.entity.Player;

public interface RelicAbility {

    public void onLeftClick(Player player, Relic relic);

    public void onRightClick(Player player, Relic relic);

}

package de.darkyiu.crops_and_magic.spells;


import org.bukkit.entity.Player;

import java.util.HashMap;

public class Mana {

    private HashMap<Player, Integer> mana = new HashMap<>();

    public int  getMana(Player player) {
        return mana.getOrDefault(player, 100);
    }

    public void setMana(Player player, int mana){
        this.mana.put(player, mana);
    }

    public boolean checkMana(Player player, Spell spell){
        if (getMana(player)<spell.getMana()){
            player.sendMessage("§cYou don't have enough mana.");
            return false;
        }
        setMana(player, (int) (getMana(player)-spell.getMana()));
        return true;
    }
}

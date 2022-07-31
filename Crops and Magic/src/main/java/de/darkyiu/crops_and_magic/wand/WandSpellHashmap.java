package de.darkyiu.crops_and_magic.wand;

import java.util.HashMap;

public class WandSpellHashmap {

    private final HashMap<String, Integer> spells = new HashMap<>();

    public void setSpells(String locName, int state){
        if (state>3){
            spells.put(locName, 1);
        }else {
            spells.put(locName, state);
        }

    }

    public int getSpell(String locName){
        return spells.getOrDefault(locName, 0);
    }

}

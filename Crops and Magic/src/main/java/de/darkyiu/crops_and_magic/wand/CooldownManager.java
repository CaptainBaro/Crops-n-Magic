package de.darkyiu.crops_and_magic.wand;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<String, Long> cooldowns = new HashMap<>();

    public void setCooldown(String spellslot, long time ){
        if(time < 1){
            cooldowns.remove(spellslot);
        }else {
            cooldowns.put(spellslot, time);
        }
    }
    public Long getCooldown(String spellslot){
        return cooldowns.getOrDefault(spellslot, (long) 0);
    }

}

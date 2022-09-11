package de.darkyiu.crops_and_magic.spells;

import de.darkyiu.crops_and_magic.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class BuffHashmap {

    private HashMap<Player, ArrayList<Buff>> buffHashmap = new HashMap<>();


    public boolean addBuff(Player player, Buff buff, int duration, int level){
        if (buffHashmap.get(player)==null){
            ArrayList arrayList = new ArrayList();
            buff.setLevel(level);
            arrayList.add(buff);
            buffHashmap.put(player, arrayList);
            runoutBuff(buff, player, duration);
            return true;
        }
        if (buffHashmap.get(player).contains(buff)){
            buffHashmap.get(player).remove(buff);
            buff.setLevel(level);
            buffHashmap.get(player).add(buff);
            runoutBuff(buff, player, duration);
            return false;
        }else {
            buff.setLevel(level);
            buffHashmap.get(player).add(buff);
            runoutBuff(buff, player, duration);
            return true;
        }
    }

    public boolean removeBuff(Player player, Buff buff){
        if (buffHashmap.get(player)==null)return false;
        if (!buffHashmap.get(player).contains(buff))return false;
        buffHashmap.get(player).remove(buff);
        return true;
    }

    public boolean hasBuff(Player player, Buff buff){
        if (buffHashmap.get(player)==null)return false;
        if (!buffHashmap.get(player).contains(buff))return false;
        return true;
    }

    public void runoutBuff(Buff buff, Player player, int duration){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!buffHashmap.containsKey(player)){
                    cancel();
                }
                if (buffHashmap.get(player).contains(buff)){
                    buffHashmap.get(player).remove(buff);
                }
            }
        }.runTaskLater(Main.getPlugin(), duration*20);
    }
}

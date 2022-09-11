package de.darkyiu.crops_and_magic.relics.relic_abilities;

import de.darkyiu.crops_and_magic.Main;
import de.darkyiu.crops_and_magic.custom_crafting.CustomItemBuilder;
import de.darkyiu.crops_and_magic.relics.Relic;
import de.darkyiu.crops_and_magic.relics.RelicAbility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Team;

import java.util.Random;

public class OreHunterRelic implements RelicAbility, Listener {
    @Override
    public void onLeftClick(Player player, Relic relic) {

    }

    @Override
    public void onRightClick(Player player, Relic relic) {
        Chunk c = player.getLocation().getChunk();
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("ores");
        team.setColor(ChatColor.DARK_GREEN);
        Team greenTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("namCol.dGreen");
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = -63;
                if (player.getWorld().getName().contains("nether")){
                    y=0;
                }
                for (;y < 128; y++) {
                    Block b = c.getBlock(x, y, z);
                    Material m = b.getType();
                    if ((m == Material.COAL_ORE)
                            || (m == Material.IRON_ORE)
                            || (m == Material.GOLD_ORE)
                            || (m == Material.DIAMOND_ORE)
                            || (m == Material.EMERALD_ORE)
                            || (m == Material.LAPIS_ORE)
                            || (m == Material.DEEPSLATE_COAL_ORE)
                            || (m == Material.DEEPSLATE_IRON_ORE)
                            || (m == Material.DEEPSLATE_GOLD_ORE)
                            || (m == Material.DEEPSLATE_DIAMOND_ORE)
                            || (m == Material.DEEPSLATE_EMERALD_ORE)
                            || (m == Material.DEEPSLATE_LAPIS_ORE)
                            || (m == Material.DEEPSLATE_REDSTONE_ORE)
                            || (m == Material.REDSTONE_ORE)
                            || (m == Material.ANCIENT_DEBRIS)) {
                        Slime slime = (Slime) b.getWorld().spawnEntity(b.getLocation().add(0.5, 0, 0.5), EntityType.SLIME);
                        slime.setSize(2);
                        slime.setAI(false);
                        slime.setInvulnerable(true);
                        slime.setInvisible(true);
                        slime.setCollidable(false);
                        slime.setGravity(false);
                        slime.setSilent(true);

                        team.addEntry(String.valueOf(slime.getEntityId()));
                        slime.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 9999, 0, false, false));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                slime.remove();
                                if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam("ores")!=null) {
                                    Bukkit.getScoreboardManager().getMainScoreboard().getTeam("ores").unregister();

                                }
                            }
                        }.runTaskLater(Main.getPlugin(), 20*10);
                    }
                }
            }
        }
    }

    @Override
    public void onEvent(EntityDeathEvent event) {
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().getKey().getKey().contains("ore")||event.getBlock().getType().equals(Material.ANCIENT_DEBRIS)){
            if (new Random().nextInt(300)==5){
                if (!Main.getPlugin().getRelicList().contains(Relic.ORE_HUNTER_RELIC.toString())){
                    event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new CustomItemBuilder(Relic.ORE_HUNTER_RELIC).createRelic());
                    Main.getPlugin().getRelicList().add(Relic.ORE_HUNTER_RELIC.toString());
                    Relic.relicFound();
                    Main.getPlugin().data.addRelics(event.getPlayer().getUniqueId(), 1);
                }
            }
        }
    }
}

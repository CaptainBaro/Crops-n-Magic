package de.darkyiu.crops_and_magic.util;

import de.darkyiu.crops_and_magic.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLocationUtil {

    private Main plugin;
    private Location location;
    private String root;

    public ConfigLocationUtil(Location location, String root){
        this.location = location;
        this.root = root;
        plugin = Main.getPlugin();
    }
    public ConfigLocationUtil(String root){
        this(null, root);
    }

    public void saveLocation(){
        FileConfiguration config = plugin.getHomesFile().getConfiguration();
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getX());
        config.set(root + ".Y", location.getY());
        config.set(root + ".Z", location.getZ());
        config.set(root + ".Yaw", location.getYaw());
        config.set(root + ".Pitch", location.getPitch());
        plugin.getHomesFile().save();
    }
    public Location loadLocation() {
        FileConfiguration config = plugin.getHomesFile().getConfiguration();
        if (config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root + ".World"));
            double x = config.getDouble(root + ".X");
            double y = config.getDouble(root + ".Y");
            double z = config.getDouble(root + ".Z");
            float yaw = (float) config.getDouble(root + ".Yaw");
            float pitch = (float) config.getDouble(root + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        }else
            return null;
    }
}

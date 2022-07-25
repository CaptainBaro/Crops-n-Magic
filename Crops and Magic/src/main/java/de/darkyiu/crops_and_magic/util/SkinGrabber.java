package de.darkyiu.crops_and_magic.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SkinGrabber{

    public static boolean changeSkin(Player player,String textures){
        GameProfile profile = ((CraftPlayer)player).getHandle().getBukkitEntity().getProfile();
        PlayerConnection connection = ((CraftPlayer)player).getHandle().b;

        connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, ((CraftPlayer)player).getHandle()));
        profile.getProperties().removeAll("textures");
        try {
            Property property = new Property("textures", textures);
            profile.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY1ODc1ODEyODA3NiwKICAicHJvZmlsZUlkIiA6ICIwMGYzM2FjMGM1NTY0OTAzYmU5NzI5NWJkNjc0YTY5NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJQQU5UQTk5ODgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMyNTFkZjQ3MDViMzM5MGI0NTcyZmVlYjMzNDJmYjY3NzY0ZDVjOTU3Nzk5ODczZGZlMTkxNmJhNTllMjM2MCIKICAgIH0KICB9Cn0=", "icl0KtPju3m96grj/qI6P0SAJIIbNG5o1zLimuD6UDM18iGpNURbAwwZFJUzgn30qii+cquAEScoKJUXxsM1zDAOogL94aWd/sSegFm1u/UC4gAuN04nslFkX68ECmMGcPKIK7/1A4Kysw+MWUxeDrsU0D3BxvHDJpU7HttN0Kq03CurbHeUPdgbHllLa2ZT4Jt/VqIPZoC/VCjQAPlcZftGHUp+YgWzVtz0L5AKS0dwn8qIyMorsGPJozrbV0rB9K4SjAGMcVHPtPX3TnAR/HoJI+8P0/uKcsgid9jlIYBgSKGu3hLx5RNZ0Zp2FT+DPVNZlvd/f5Guy1wITzZ2GuWLwg8s6SQrC5ogKS/3631Q0zhV48xgpTITWj8l0XoQ3tFNzwdUszeKw82h7P/pROJjrDyzckUX/6TC10g0XnopywRAisCpvIKW/b0hwCH5SfdAl5rXlvCjP91qgMLV+4j8DKm2FMWut+uu1KfDojK5bTP2h1oftvsexL+2dlQvauSZv6sKFIa5BzdjJHDZgTXssvPmEc+koimNj7GNmmAfaOn04nu9Qc4nGlXq7SbKs1BD0lqTBBO7lIPV0bKTw0HhVY3V5lZVw711KizKMkYExgZjP2kiPA1kYW4JWJaK3VTU+gWGojsgb4NddLfUJchy3O6BQryAEvqtpjFX890="));
        }catch (Exception e){
            connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ((CraftPlayer)player).getHandle()));
            return false;
        }
        connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ((CraftPlayer)player).getHandle()));
        return true;
    }

}

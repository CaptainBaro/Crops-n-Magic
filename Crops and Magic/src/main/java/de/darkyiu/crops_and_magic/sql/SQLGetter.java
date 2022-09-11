package de.darkyiu.crops_and_magic.sql;

import de.darkyiu.crops_and_magic.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Main plugin;
    public SQLGetter(Main plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS PLAYERDATA"
            + "(UUID VARCHAR(100), NAME VARCHAR(100), POINTS INT(100), DEATHS INT(100), RELICS INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createPLayer(Player player){
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO PLAYERDATA"
                        + " (UUID, NAME) VALUES (?,?)");
                ps2.setString(1, uuid.toString());
                ps2.setString(2, player.getName());
                ps2.executeUpdate();

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM PLAYERDATA WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if (results.next()){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addPoints(UUID uuid, int points){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PLAYERDATA SET POINTS=? WHERE  UUID=?");
            ps.setInt(1, (getPoints(uuid) + points));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getPoints(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT  POINTS FROM PLAYERDATA WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet resultSet = ps.executeQuery();
            int points = 0;
            if(resultSet.next()){
                points = resultSet.getInt("POINTS");
                return points;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //DELETE STUFF

    public void emptyTable(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE  PLAYERDATA");
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void remove(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM PLAYERDATA WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addDeaths(UUID uuid, int deaths){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PLAYERDATA SET DEATHS=? WHERE  UUID=?");
            ps.setInt(1, (getDeaths(uuid) + deaths));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getDeaths(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT  DEATHS FROM PLAYERDATA WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet resultSet = ps.executeQuery();
            int deaths  = 0;
            if(resultSet.next()){
                deaths = resultSet.getInt("DEATHS");
                return deaths;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addRelics(UUID uuid, int relics){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PLAYERDATA SET RELICS=? WHERE  UUID=?");
            ps.setInt(1, (getRelics(uuid) + relics));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getRelics(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT  RELICS FROM PLAYERDATA WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet resultSet = ps.executeQuery();
            int relics  = 0;
            if(resultSet.next()){
                relics = resultSet.getInt("RELICS");
                return relics;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


}

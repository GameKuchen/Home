package de.gamekuchen.home;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class setHome implements CommandExecutor {
    private setHome Plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        File ordner = new File("plugins/Home/players/");
        File file = new File("plugins/Home/players/" + p.getUniqueId(),args[0] + ".yml");
        if(!ordner.exists()) ordner.mkdir();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        if(p.hasPermission("Home.set") || p.isOp()){
            if(args.length == 1){
                if(!file.exists()){
                    Location loc = p.getLocation();
                    double x = loc.getX();
                    double y = loc.getY();
                    double z = loc.getZ();
                    double yaw = loc.getYaw();
                    double pitch = loc.getPitch();
                    String worldname = loc.getWorld().getName();
                    yamlConfiguration.set("X", Double.valueOf(x));
                    yamlConfiguration.set("Y", Double.valueOf(y));
                    yamlConfiguration.set("Z", Double.valueOf(z));
                    yamlConfiguration.set("Yaw", Double.valueOf(yaw));
                    yamlConfiguration.set("Pitch", Double.valueOf(pitch));
                    yamlConfiguration.set("Worldname", worldname);
                    try{
                        yamlConfiguration.save(file);

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    p.sendMessage(String.valueOf(Main.prefix)+ "§eDu hast das Home §6§l" + args[0] + " §egesetzt!");
                    p.sendTitle("§8(§a§l!§r§8) §a§l§oHomeSet §r §8(§a§l!§r§8)", " §aYour new home has been set", 10, 70, 20);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                }else {
                    p.sendMessage(String.valueOf(Main.prefix) + "§eDas Home §6§l" + args[0] + " §egibt es schon!");
                }
            }
        }else {
            p.sendMessage(Main.noPerms);
        }


        return false;
    }
}

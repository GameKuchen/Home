package de.gamekuchen.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class home implements CommandExecutor{
    static File file;
    Player p;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        p = (Player) sender;
        file = new File("plugins/Home/players/" + p.getUniqueId(), args[0] + ".yml");

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        if(args[0].length() == 0)
            file.list();
        if(args.length == 1){
            if(file.exists()){
                double x = yamlConfiguration.getDouble("X");
                double y = yamlConfiguration.getDouble("Y");
                double z = yamlConfiguration.getDouble("Z");
                double yaw = yamlConfiguration.getDouble("Yaw");
                double pitch = yamlConfiguration.getDouble("Pitch");
                String worldname = yamlConfiguration.getString("Worldname");
                Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
                loc.setPitch((float) pitch);
                loc.setYaw((float) yaw);
                p.teleport(loc);
                p.sendMessage(String.valueOf(Main.prefix) + "§eDu bist nun bei deinem Home §6§l" + args[0]);
                p.sendTitle("§a§l Teleportiert", "§dHome sweet §5home", 10, 70, 20);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, 0);

            }else{
                p.sendMessage(String.valueOf(Main.prefix) + "§eDas Home §6§l" + args[0] + " §eexistiert nicht!");
            }

        }

        return false;
    }

}


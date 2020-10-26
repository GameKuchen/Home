package de.gamekuchen.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstructTabCompleter implements TabCompleter {
    Player p;
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("home")){
            p = (Player)sender;
            if(sender instanceof Player){
                File dir = new File("plugins/Home/players/" + p.getUniqueId());

                return Stream.of(dir.listFiles())
                        .map(File::getName)
                        .map(f -> f.replace(".yml", ""))
                        .collect(Collectors.toList());
            }
        }
        if(command.getName().equalsIgnoreCase("delhome")){
            p = (Player)sender;
            if(sender instanceof Player){
                File dir = new File("plugins/Home/players/" + p.getUniqueId());

                return Stream.of(dir.listFiles())
                        .map(File::getName)
                        .map(f -> f.replace(".yml", ""))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}

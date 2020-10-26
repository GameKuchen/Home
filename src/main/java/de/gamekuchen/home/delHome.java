package de.gamekuchen.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.processing.FilerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class delHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player )sender;
        File file = new File("plugins/Home/players/" + p.getUniqueId(), args[0] + ".yml");
        if(args.length == 1){
            file.delete();
            p.sendMessage("§eDein Home §6§l " + args[0] + " §ewurde gelöscht!");
        }
        return false;
    }
}

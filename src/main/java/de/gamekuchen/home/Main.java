package de.gamekuchen.home;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    static final String prefix = "§6●§eHome §8|";
    static final String noPerms = "§7Dazu hast du §c$lkeine §7Rechte!";

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN +"-----------------------------");
        System.out.println(ChatColor.GREEN +"Home-Plugin wurde geladen!");
        System.out.println(ChatColor.GREEN +"-----------------------------");
        instance = this;
        getCommands();
        loadConfig();
        ItemStack item = new ItemStack(Material.NAME_TAG);
        NamespacedKey key = new NamespacedKey(this, "craftableNameTag");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("  S", " G ", "P  ");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);

    }
    private void getCommands() {
        this.getCommand("sethome").setExecutor(new setHome());
        this.getCommand("home").setExecutor(new home());
        this.getCommand("delhome").setExecutor(new delHome());
        this.getCommand("home").setTabCompleter(new ConstructTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void onJoin (PlayerJoinEvent e) {}
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}

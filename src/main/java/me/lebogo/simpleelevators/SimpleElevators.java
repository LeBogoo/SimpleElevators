package me.lebogo.simpleelevators;

import org.bukkit.plugin.java.JavaPlugin;


import java.util.logging.Logger;

public final class SimpleElevators extends JavaPlugin {
    public static Logger LOGGER = Logger.getLogger("SimpleElevators");
    public static SimpleElevators INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerSneakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJumpListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

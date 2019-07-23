package de.primeplugins.primerand.main;

import de.primeplugins.primerand.commands.RandCMD;
import de.primeplugins.primerand.listener.RandInvHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrimeRand extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("rand").setExecutor(new RandCMD(this));
        Bukkit.getPluginManager().registerEvents(new RandInvHandler(this),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
